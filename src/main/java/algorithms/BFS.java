package algorithms;

import models.State;
import solver.Solution;

import java.util.HashSet;
import java.util.List;

public class BFS extends CommonSearcher {
    @Override
    Solution searchAlgorithm(Searchable s) {
        addToOpenList(s.getInitialState());
        HashSet<State> closedSet = new HashSet<>();

        while (!openList.isEmpty()) {
            State state = popOpenList();
            closedSet.add(state);

            if (s.isGoal(state)) {
                return backtraceSolution(state);
            }

            List<State> allPossibleStates = s.getAllPossibleStates(state);

            for (State possibleState: allPossibleStates) {
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
