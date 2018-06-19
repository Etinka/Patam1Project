package models;


public class State<T extends Comparable<T>> implements Comparable<State<T>> {
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


    @Override
    public int compareTo(State<T> o) {
        return state.compareTo(o.state);
    }
}