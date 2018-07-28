package models;

public class StateImpl<T> implements State<T> {
    private T state;
    private State<T> cameFrom;
    private double cost;
    private int hashCode;
    private Step step;

    public StateImpl(T state) {
        this(state, null);
    }

    public StateImpl(T state, Step step) {
        this.state = state;
        this.step = step;
        this.hashCode = hashCode();
    }

    @Override
    public T getState() {
        return state;
    }

    @Override
    public void setCameFrom(State<T> n) {
        this.cameFrom = n;
    }

    @Override
    public State<T> getCameFrom() {
        return cameFrom;
    }

    @Override
    public Step getStep() {
        return step;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public void printState() {

    }

    @Override
    public int compareTo(State<T> o) {
        return Double.compare(cost, o.getCost());
    }
}
