package solver;

import java.io.Serializable;
import java.util.ArrayList;

public class MySolution<T> implements Solution<T>, Serializable {
    private ArrayList<T> steps;


    public MySolution(ArrayList<T> steps) {
        //Move to solution
        this.steps = steps;
    }


    @Override
    public void printSteps() {
        for (T step : steps) {
            System.out.println(step);
            System.out.println();
        }
    }


}
