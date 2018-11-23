package server;

import cache.CacheManager;
import cache.FileCacheManager;
import models.PuzzleStep;
import solver.MySolver;
import solver.PipesPuzzleSolution;
import solver.Solution;
import solver.Solver;

import java.io.*;

/**
 * has the responsibility of closing the streams
 */
public class PipesPuzzleClientHandler implements ClientHandler {
    private CacheManager cacheManager = new FileCacheManager();
    private Solver solver = new MySolver();

    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        int numRows = 0;
        int numCol = 0;
        BufferedReader inFClient = new BufferedReader(new InputStreamReader(inFromClient));
        try {
            //Getting level from user
            System.out.println("******** start of input from user");
            String line;
            StringBuilder builder = new StringBuilder();
            while (!(line = inFClient.readLine()).equals("done")) {
                builder.append(line);
                numRows++;
                numCol = line.length();
                //System.out.println(line);
            }

            String level = builder.toString();
            startSolution(numRows, numCol, level, outToClient);
            inFClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleClient(int numRows, int numCol, String level, OutputStream outToClient) {
        startSolution(numRows, numCol, level, outToClient);
    }

    private void startSolution(int numRows, int numCol, String level, OutputStream outToClient) {
        //Getting solution from the cache manager
        PrintWriter outTC = new PrintWriter(outToClient);

        Solution solution = cacheManager.load(level);
        if (solution == null) {
            //solver
            solution = solver.solve(convertStringToChar(level, numRows, numCol), numRows, numCol);
            cacheManager.store(level, solution);
        }

        PipesPuzzleSolution pipesPuzzleSolution = new PipesPuzzleSolution(solution);

//        System.out.println("Printing Solution steps: ");
//        pipesPuzzleSolution.printSteps();

        for (PuzzleStep step : pipesPuzzleSolution.getSteps()) {
            outTC.println(step.toString());
        }

        outTC.println("done");
        outTC.flush();
        outTC.close();
    }

    private char[][] convertStringToChar(String levelString, int rowNum, int colNum) {
        char[][] level = new char[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            level[i] = new char[colNum];
            level[i] = levelString.substring(i * colNum, (i * colNum) + colNum).toCharArray();
        }
        return level;
    }
}
