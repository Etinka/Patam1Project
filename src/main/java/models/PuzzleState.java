package models;


import java.io.Serializable;
import java.util.Arrays;

public class PuzzleState implements State, Serializable {
    private char[][] state;
    private State<char[][]> cameFrom;
    private double cost;
    private int rowNum;

    public PuzzleState(char[][] state, int rowNum) {
        this.state = state;
        this.rowNum = rowNum;
    }

    @Override
    public char[][] getState() {
        return state;
    }

    @Override
    public State getCameFrom() {
        return cameFrom;
    }

    @Override
    public Step getStep() {
        if (cameFrom == null) {
            return null;
        }
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < state[i].length; j++) {
                if (state[i][j] != cameFrom.getState()[i][j]) {
                    return new Step(i, j, 1);
                }
            }

        }
        return null;
    }

    @Override
    public void setCameFrom(State n) {
        cameFrom = n;
    }

    @Override
    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public void printState() {
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < state[i].length; j++) {
                System.out.print(state[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public int compareTo(Object o) {
        return Double.compare(cost, ((State) o).getCost());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PuzzleState that = (PuzzleState) o;
        return Arrays.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(state);
    }
}