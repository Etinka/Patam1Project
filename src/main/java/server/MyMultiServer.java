package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyMultiServer implements Server {
    private ServerSocket serverSocket;
    private int port;
    private boolean stop = false;
    private ThreadPoolExecutor threadPoolExecutor;
    private int clientsCount = 0;

    public MyMultiServer(int port, int threadsNumber) {
        this.port = port;
        this.threadPoolExecutor = new ThreadPoolExecutor(threadsNumber, threadsNumber, 10, TimeUnit.SECONDS,
                new PriorityBlockingQueue<>());
    }

    @Override
    public void start(ClientHandler clientHandler) {
        new Thread(() -> {
            try {
                startServer(clientHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void startServer(ClientHandler clientHandler) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(5000);
        System.out.println("Server connected - waiting for client");

        while (!stop) {
            try {
                Socket aClient = serverSocket.accept();
                clientsCount++;
                System.out.println("*** client number " + clientsCount + " is connected ***");

                int numRows = 0;
                int numCol = 0;
                BufferedReader inFClient = new BufferedReader(new InputStreamReader(aClient.getInputStream()));
                //Getting level from user
                String line;
                StringBuilder builder = new StringBuilder();
                while (!(line = inFClient.readLine()).equals("done")) {
                    builder.append(line);
                    numRows++;
                    numCol = line.length();
                    System.out.println(line);
                }
//                System.out.println("end of input");

                int finalNumCol = numCol;
                int finalNumRows = numRows;

                MultiServerPriorityRunnable priorityRunnable = new MultiServerPriorityRunnable(finalNumRows * finalNumCol) {
                    @Override
                    public void run() {
                        try {
                            clientHandler.handleClient(finalNumRows, finalNumCol, builder.toString(), aClient.getOutputStream());
                            aClient.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };

                threadPoolExecutor.execute(priorityRunnable);

            } catch (SocketTimeoutException e) {
                System.out.println("Client did not connect...");
            }
        }
        threadPoolExecutor.shutdown();
        serverSocket.close();

    }

    @Override
    public void stop() {
        stop = true;
    }
}
