package algorithms;

import models.State;
import solver.Solution;

import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class DFS <T extends Comparable<T>> extends CommonSearcher<T>  {
    @Override
    Solution searchAlgorithm(Searchable s) {
        Stack<State> stack = new Stack();
        stack.add(s.getInitialState());

        HashSet<State> closedSet = new HashSet<>();
        while (!stack.isEmpty()) {
            State state = stack.pop();
            addNode();
            closedSet.add(state);

            if (s.isGoal(state)) {
                return backtraceSolution(state);
            }

            List<State> allPossibleStates = s.getAllPossibleStates(state);

            for (State possibleState: allPossibleStates) {
                if (!closedSet.contains(possibleState) && !stack.contains(possibleState)) {
                    possibleState.setCameFrom(state);
                    possibleState.setCost(state.getCost() + 1);
                    stack.add(possibleState);
                }
            }
        }

        return null;
    }
}
