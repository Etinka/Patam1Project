package algorithms;

import models.State;
import solver.Solution;

import java.util.List;
import java.util.Random;

public class HillClimbing2 extends CommonSearcher {
    @Override
    <T> Solution searchAlgorithm(Searchable<T> s) {
        State<T> curState = s.getInitialState();
        curState.setGrade(s.grade(curState));

        while (true) {
            addNode();
            if (s.isGoal(curState)) {
                return backtraceSolution(s.getInitialState(), curState);
            }

            List<State<T>> allPossibleStates = s.getAllPossibleStates(curState);

            for (State<T> possibleState: allPossibleStates) {
                possibleState.setCameFrom(curState);
            }

            if (Math.random() > 0.5) {
                Random rnd = new Random();
                int rndInd = rnd.nextInt(allPossibleStates.size());
                curState = allPossibleStates.get(rndInd);
                continue;
            }

            double grade = Double.MAX_VALUE;
            for (State<T> possibleState : allPossibleStates) {
                possibleState.setGrade(s.grade(possibleState));
                if (possibleState.getGrade() < grade) {
                    grade = possibleState.getGrade();
                    curState = possibleState;
                }
            }

        }
    }
}