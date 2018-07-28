package algorithms;

import models.State;
import solver.Solution;

import java.util.HashSet;
import java.util.List;

public class BestFirstSearch extends CommonSearcher {

    @Override
    <T> Solution searchAlgorithm(Searchable<T>  s) {
        addToOpenList(s.getInitialState());
        HashSet<State> closedSet = new HashSet<>();

        while (!openList.isEmpty()) {
            State<T> state = popOpenList();
            closedSet.add(state);

            if (s.isGoal(state)) {
//                System.out.println("s.isGoal(n)");
                return backtraceSolution(state, s.getInitialState());
            }

            List<State<T>> allPossibleStates = s.getAllPossibleStates(state);

            for (State<T> possibleState : allPossibleStates) {
                if (!closedSet.contains(possibleState)) {
                    possibleState.setCameFrom(state);
                    possibleState.setCost(state.getCost() + 1);
                    if (!openList.contains(possibleState)) {
                        addToOpenList(possibleState);
                    } else if (openList.removeIf(e -> e.equals(possibleState) && e.getCost() > possibleState.getCost())) {
                        //Removes the "state" object from the queue if the cost is higher then the new state cost
                        //And then adding it again with the new cost
                        addToOpenList(possibleState);
                    }
                }
            }
        }

        return null;
    }


}