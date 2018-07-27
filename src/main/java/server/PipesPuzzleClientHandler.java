package server;

import cache.CacheManager;
import cache.FileCacheManager;
import models.Step;
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
        PrintWriter outTC = new PrintWriter(outToClient);
        BufferedReader inFClient = new BufferedReader(new InputStreamReader(inFromClient));
        try {
            //Getting level from user
            String line;
            StringBuilder builder = new StringBuilder();
            while (!(line = inFClient.readLine()).equals("done")) {
                builder.append(line);
                numRows++;
                numCol = line.length();
//                System.out.println(line);
            }

            String level = builder.toString();
            //Getting solution from the cache manager
            Solution solution = cacheManager.load(level);
            if (solution == null) {
                //solver
                solution = solver.solve(convertStringToChar(level, numRows, numCol), numRows, numCol);
                cacheManager.store(level, solution);
            }

            PipesPuzzleSolution pipesPuzzleSolution = new PipesPuzzleSolution(solution);

//            System.out.println("Printing Solution steps: ");
//            pipesPuzzleSolution.printSteps();

            for (Step step : pipesPuzzleSolution.getSteps()) {
                outTC.println(step.toString());
            }

            outTC.println("done");

//            System.out.println("done");
            outTC.flush();
            inFClient.close();
            outTC.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
