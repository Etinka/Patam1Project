package server;

import cache.CacheManager;
import cache.MyCacheManager;
import solver.MySolver;
import solver.Solution;
import solver.Solver;

import java.io.*;

/**
 * has the responsibility of closing the streams
 */
public class MyClientHandler implements ClientHandler {
    private CacheManager cacheManager = new MyCacheManager();
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
                builder.append("\n");
                numRows++;
                numCol = line.length();
            }
            //Converting to Solution
//            Solution<String> level = new MySolution<S>(builder.toString(), new ArrayList<>());
//            System.out.println("Printing Level:");
//            level.printFinalBoard();
//            System.out.println();
            String level = builder.toString();
            //Getting solution from the cache manager
            Solution solution = cacheManager.load(level);
            if (solution == null) {
                //solver
                solution = solver.solve(level, numRows, numCol);
                cacheManager.store(level, solution);
            }
//            System.out.println("Printing Solution:");
//            solution.printFinalBoard();
            System.out.println("Printing Solution steps:");
            solution.printSteps();

            //sending to user
//            outTC.println(solution.getFinalBoard());
            outTC.flush();

            System.out.println("done");

            inFClient.close();
            outTC.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
