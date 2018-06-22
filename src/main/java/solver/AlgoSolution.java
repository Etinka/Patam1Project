package solver;

import models.State;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class AlgoSolution<T extends Comparable<T>> implements Solution, Serializable {
    private ArrayList<State<T>> steps;

    public AlgoSolution(ArrayList<State<T>> steps) {
        //Move to solution
        this.steps = steps;
    }


    @Override
    public void reverse() {
        Collections.reverse(steps);
    }

    @Override
    public void printSteps() {
        for (State<T> step : steps) {
            System.out.println(step.getState());
            System.out.println();
        }
    }
}
