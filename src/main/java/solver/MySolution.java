package solver;

import algorithms.State;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MySolution<T> implements Solution<T>, Serializable {
    private T finalBoard;
    private List<State<T>> steps;


    public MySolution(T finalBoard, ArrayList<State<T>> steps) {
        //Move to solution
        this.finalBoard = finalBoard;
        this.steps = steps;
    }

    @Override
    public T getFinalBoard() {
        return finalBoard;
    }

    @Override
    public void printFinalBoard() {
        System.out.println(finalBoard);
    }

    @Override
    public void printSteps() {
        for (State<T> step : steps) {
            System.out.println(step.getState());
            System.out.println();
        }
    }


}
