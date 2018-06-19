package algorithms;


public class State<T> {
    private T state;
    private State<T> cameFrom;

    public State(T state) {
        this.state = state;
    }

    public T getState() {
        return state;
    }

    public State<T> getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(State<T> n) {
        cameFrom = n;
    }
}