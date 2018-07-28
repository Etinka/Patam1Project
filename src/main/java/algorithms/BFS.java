package algorithms;

import models.State;
import solver.Solution;

import java.util.HashSet;
import java.util.List;

public class BFS extends CommonSearcher {
    @Override
    <T> Solution searchAlgorithm(Searchable<T>  s) {
        addToOpenList(s.getInitialState());
        HashSet<State<T>> closedSet = new HashSet<>();

        while (!openList.isEmpty()) {
            State<T> state = popOpenList();
            closedSet.add(state);

            if (s.isGoal(state)) {
                return backtraceSolution(state, s.getInitialState());
            }

            List<State<T>> allPossibleStates = s.getAllPossibleStates(state);

            for (State<T> possibleState: allPossibleStates) {
                if (!closedSet.contains(possibleState) && !openList.contains(possibleState)) {
                    possibleState.setCameFrom(state);
                    possibleState.setCost(state.getCost() + 1);
                    addToOpenList(possibleState);
                }
            }

        }

        return null;
    }
}
