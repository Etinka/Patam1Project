package algorithms;

import models.State;
import solver.Solution;

import java.util.ArrayList;
import java.util.HashSet;

public class BestFirstSearch<T extends Comparable<T>> extends CommonSearcher<T> {

    @Override
    Solution searchAlgorithm(Searchable<T> s) {
        HashSet<State<T>> closedSet = new HashSet<>();
        addToOpenList(s.getInitialState());

        while (!openList.isEmpty()) {
            State<T> n = popOpenList();
            closedSet.add(n);

            if (s.isGoal(n)) {
                System.out.println("s.isGoal(n)");
                return backtraceSolution(n);
            }
            ArrayList<State<T>> successors = s.getAllPossibleStates(n);
            for (State<T> state : successors) {
                if (!closedSet.contains(state)) {
                    state.setCameFrom(n);
                    state.setCost(state.getCost() + 1);
                    if (!openList.contains(state)) {
                        addToOpenList(state);
                    } else if (openList.removeIf(e -> e.equals(state) && e.getCost() > state.getCost())) {
                        //Removes the "state" object from the queue if the cost is higher then the new state cost
                        //And then adding it again with the new cost
                        addToOpenList(state);
                    }
                }
            }

        }

        return null;
    }

}