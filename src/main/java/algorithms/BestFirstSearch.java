package algorithms;

import models.State;
import solver.Solution;

import java.util.ArrayList;
import java.util.HashSet;

public class BestFirstSearch<T extends Comparable<T>> extends CommonSearcher<T> {

    @Override
    Solution searchAlgorithm(Searchable s) {
        addToOpenList(s.getInitialState());
        HashSet<State> closedSet = new HashSet<>();

        while (!openList.isEmpty()) {
            State n = popOpenList();
            closedSet.add(n);

            if (s.isGoal(n)) {
                System.out.println("s.isGoal(n)");
                return backtraceSolution(n);
            }

            ArrayList<State> allPossibleStates = s.getAllPossibleStates(n);

//            if (successors != null) {
            for (State possibleState : allPossibleStates) {
                if (!closedSet.contains(possibleState)) {
                    possibleState.setCameFrom(n);
                    possibleState.setCost(n.getCost() + 1);
                    if (!openList.contains(possibleState)) {
                        addToOpenList(possibleState);
                    } else if (openList.removeIf(e -> e.equals(possibleState) && e.getCost() > possibleState.getCost())) {
                        //Removes the "state" object from the queue if the cost is higher then the new state cost
                        //And then adding it again with the new cost
                        addToOpenList(possibleState);
                    }
                }
            }
//            }
        }

        return null;
    }


}