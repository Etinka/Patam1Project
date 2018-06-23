package solver;

import models.State;

import java.util.List;

public interface Solution<T> {
    List<State<T>> getSteps();
    void reverse();
}
