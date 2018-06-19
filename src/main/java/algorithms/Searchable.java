package algorithms;

import models.State;

import java.util.ArrayList;

public interface Searchable <T extends Comparable<T>> {
    State<T> getInitialState();
    boolean isGoal(State<T> state);
    ArrayList<State<T>> getAllPossibleStates(State<T> s);
}
