package models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;

import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class PipesGameModel {

    private Socket serverSocket;
    public BooleanProperty isGoal;
    public IntegerProperty numberOfSteps;
    public ListProperty<char[]> gameBoard;


    public void connect(String ip, String port) throws IOException {
        int portNum = Integer.parseInt(port);
        this.serverSocket = new Socket(ip, portNum);
        System.out.println("Connected to server");
    }

    public void disconnect() throws IOException{
        if (this.serverSocket != null) {
            this.serverSocket.close();
        }
    }
}
