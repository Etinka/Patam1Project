package server;

import java.io.*;

/**
 * has the responsibility of closing the streams
 */
public class MyClientHandler implements ClientHandler {

    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        PrintWriter outTC = new PrintWriter(outToClient);
        BufferedReader inFClient = new BufferedReader(new InputStreamReader(inFromClient));
        try {
            String line;
            StringBuilder builder = new StringBuilder();
            while (!(line = inFClient.readLine()).equals("done")) {
                builder.append(line);
                builder.append("\n");
            }
            System.out.println(builder.toString());
            System.out.println("done");
            inFClient.close();
            outTC.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
