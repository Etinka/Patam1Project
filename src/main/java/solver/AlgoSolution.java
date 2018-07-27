package solver;

import models.State;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class AlgoSolution implements Solution, Serializable {
    private List<State<?>> steps;

    public AlgoSolution(List steps) {
        //Move to solution
        this.steps = steps;
    }

    @Override
    public List<State<?>> getSteps() {
        return steps;
    }

    @Override
    public void reverse() {
        Collections.reverse(steps);
    }


}
