package algorithms;

import java.util.ArrayList;
import java.util.List;

public class PipesPuzzle implements Searchable<String> {
    private State<String> initialState;
    private State<String> goalState;

    public PipesPuzzle(State<String> initialState, State<String> goalState) {
        this.initialState = initialState;
        this.goalState = goalState;
    }

    @Override
    public State<String> getInitialState() {
        return initialState;
    }

    @Override
    public State<String> getGoalState() {
        return goalState;
    }

    @Override
    public boolean isGoal(State<String> state) {
        return state.getState().equalsIgnoreCase(goalState.getState());
    }

    @Override
    public ArrayList<State<String>> getAllPossibleStates(State<String> s) {
        ArrayList<State<String>> possibleStates = new ArrayList<>();

        for (int i = 1; i < initialState.getState().length() - 1; i++) {
            StringBuilder newState = new StringBuilder(s.getState());
            newState.setCharAt(i, getNextChar(newState.charAt(i)));
            possibleStates.add(new State<>(newState.toString()));
        }
        return possibleStates;
    }

    private char getNextChar(char c) {
        switch (c) {
            case '|':
                return '-';
            case '-':
                return '|';
            case 'L':
                return 'F';
            case 'F':
                return '7';
            case '7':
                return 'J';
            case 'J':
                return 'L';
            default:
                return '-';
        }
    }
}
