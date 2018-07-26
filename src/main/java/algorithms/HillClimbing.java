package algorithms;

import models.State;
import solver.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HillClimbing <T extends Comparable<T>> extends CommonSearcher<T> {
    @Override
    Solution searchAlgorithm(Searchable s) {
        State curState = s.getInitialState();
        State bestNextState = null;

        while (true) {
            List<State> allPossibleStates = new ArrayList<>(s.getAllPossibleStates(curState));
            for(State possibleState : allPossibleStates) {
                possibleState.setCameFrom(curState);
            }
            double grade = Double.MAX_VALUE;
            if (Math.random() < 0.7) {
                for (State possibleState : allPossibleStates) {
                    addNode();
                    double g = s.grade(possibleState);
                    if (g < grade ) {
                        bestNextState = possibleState;
                        grade = g;
                    }
                }

                if (bestNextState == null ) {
                    bestNextState = curState;
                }

                if (s.isGoal(bestNextState)) {
                    return backtraceSolution(bestNextState);
                }

                if (s.grade(curState) > s.grade(bestNextState)) {
                    curState = bestNextState;
                }
            }

            else {
                if (allPossibleStates.isEmpty()) {
                    break;
                }

                Random rnd = new Random();
                int rndInd = rnd.nextInt(allPossibleStates.size());
                curState = allPossibleStates.get(rndInd);
            }
        }

        return null;
    }
}
