package algorithms;

import models.State;

import java.util.List;

public interface Searchable<T> {
    State<T> getInitialState();

    boolean isGoal(State<T> state);

    List<State<T>> getAllPossibleStates(State<T> s);

    int grade(State<T> state);
}
