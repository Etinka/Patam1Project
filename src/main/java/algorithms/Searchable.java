package algorithms;

import models.State;

import java.util.ArrayList;

public interface Searchable <T> {
    State<T> getInitialState();
    State<T> getGoalState();
    boolean isGoal(State<T> state);
    ArrayList<State<T>> getAllPossibleStates(State<T> s);
}
