package server;

import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler {
    void handleClient(InputStream inFromClient, OutputStream outToClient);

    void handleClient(int numRows, int numCol, String level, OutputStream outToClient);
}
