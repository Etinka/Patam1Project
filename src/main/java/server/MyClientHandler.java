package server;

import cache.CacheManager;
import cache.MyCacheManager;
import solver.MySolution;
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
                numRows++;
                numCol = line.length();
            }
            //Converting to Solution
            Solution level = new MySolution(builder.toString(), numRows, numCol);
            System.out.println("Printing Level:");
            level.print();
            System.out.println("done");

            //Getting solution from the cache manager
            Solution solution = cacheManager.load(level.getLevelString());
            if (solution == null) {
                //solver
                solution = solver.solve(level.getLevelString());
                cacheManager.store(level.getLevelString(), solution);
            }
            System.out.println("Printing Solution:");
            solution.print();

            //sending to user
            outTC.println(solution.getLevelString());
            outTC.flush();

            System.out.println("done");

            inFClient.close();
            outTC.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
