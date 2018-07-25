package server;

public interface Server {
    void start(ClientHandler clientHandler);
    void stop();
}
