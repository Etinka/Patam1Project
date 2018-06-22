package algorithms;

import models.PuzzleState;
import models.State;

import java.util.ArrayList;

public interface Searchable <T> {
    State<T> getInitialState();
    boolean isGoal(State<T> state);
    ArrayList<State<T>> getAllPossibleStates(State<T> s);
}
