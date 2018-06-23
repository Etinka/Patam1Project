package solver;

import models.State;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgoSolution<T extends Comparable<T>> implements Solution, Serializable {
    private List<State<T>> steps;

    public AlgoSolution(List<State<T>> steps) {
        //Move to solution
        this.steps = steps;
    }

    @Override
    public List<State<T>> getSteps() {
        return steps;
    }

    @Override
    public void reverse() {
        Collections.reverse(steps);
    }


}
