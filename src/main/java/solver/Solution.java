package solver;

import models.State;

import java.util.ArrayList;

public interface Solution<T> {
    ArrayList<State<T>> getSteps();
    void reverse();
}
