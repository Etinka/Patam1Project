package models;

import algorithms.PipesPuzzle;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PipesGameModel {
    private ScheduledExecutorService executor;

    private Socket serverSocket;
    public IntegerProperty stepsNum = new SimpleIntegerProperty();
    public IntegerProperty secondsPassed = new SimpleIntegerProperty();
    public ListProperty<char[]> gameBoard = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));

    private char[][] mazeData = {
            {'s', 'L', 'F'},
            {'-', '-', 'F'},
            {'|', '7', 'g'}
//            {'s', 'L', 'F', '-', 'J', '7', '7', '7', '7'},
//            {'7', '7', '7', '7', '7', '7', '7', '7', '7'},
//            {'7', '7', '7', '7', '7', '7', '7', '7', '7'},
//            {'7', '7', '7', '7', '|', '7', '7', '7', '7'},
//            {'7', '7', '7', '7', 'L', '7', '7', '7', '7'},
//            {'7', '7', '7', '7', '7', '7', '7', '7', '7'},
//            {'7', '7', '7', '7', '|', '7', '7', '7', '7'},
//            {'7', '7', '-', '-', '-', '-', '-', '-', 'g'},
    };

    public char[][] getMazeData() {
        return mazeData;
    }

    public boolean isGameStarted() {
        return executor != null;
    }

    public void start() {
        if (gameBoard.size() == 0) {
            gameBoard.addAll(mazeData);
        }
        setTimer();
    }

    public void stop() {
        if (executor != null) {
            executor.shutdown();
        }
        executor = null;
    }

    private void setTimer() {
        Runnable helloRunnable = () -> Platform.runLater(() -> secondsPassed.setValue(secondsPassed.get() + 1));

        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(helloRunnable, 0, 1, TimeUnit.SECONDS);
    }

    public void connect(String ip, String port) throws IOException {
        int portNum = Integer.parseInt(port);
        this.serverSocket = new Socket(ip, portNum);
        System.out.println("Connected to server");
    }

    public void disconnect() throws IOException {
        if (this.serverSocket != null) {
            this.serverSocket.close();
        }
    }

    public void movePipe(int row, int col) {
        char current = mazeData[row][col];
        if (current != 's' && current != 'g' && current != ' ') {
            stepsNum.set(stepsNum.get() + 1);
        }
        mazeData[row][col] = PipesPuzzle.getNextChar(mazeData[row][col]);
        this.gameBoard.set(col, this.gameBoard.get(col));
    }

    public void saveGame(File file) throws IOException {
        PrintWriter out = new PrintWriter(file);
        for (char[] aGameBoard : this.gameBoard) {
            out.println(new String(aGameBoard));
        }
        out.close();
    }

    public void solve() throws IOException, InterruptedException {
        if (this.serverSocket != null) {
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(this.serverSocket.getInputStream()));
            PrintWriter outToServer = new PrintWriter(this.serverSocket.getOutputStream());

            for (char[] line : this.gameBoard.get()) {
                outToServer.println(line);
                outToServer.flush();
            }

            outToServer.println("done");
            outToServer.flush();
            System.out.println("flushed to server");
            String line;
            while (!(line = inFromServer.readLine()).equals("done")) {
                String[] steps = line.split(",");
                int row = Integer.parseInt(steps[0]);
                int col = Integer.parseInt(steps[1]);
                int step = Integer.parseInt(steps[2]);

                for (int i = 1; i <= step; i++) {
                    Platform.runLater(() -> movePipe(row, col));
                    Thread.sleep(150);
                }
            }
            System.out.println("done");
        }
    }

    public void loadGame(String fileName) throws IOException {
        List<char[]> mazeBuilder = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            mazeBuilder.add(line.toCharArray());
        }
        this.gameBoard.setAll(mazeBuilder.toArray(new char[mazeBuilder.size()][]));
        reader.close();
    }

}
