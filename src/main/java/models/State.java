package models;


import java.io.Serializable;

public class State<T extends Comparable<T>> implements Comparable<State<T>>, Serializable {
    private T state;
    private State<T> cameFrom;
    private double cost;

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

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }


}