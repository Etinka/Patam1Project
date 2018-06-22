package algorithms;

import models.State;
import solver.AlgoSolution;
import solver.Solution;

import java.util.ArrayList;
import java.util.PriorityQueue;

abstract class CommonSearcher<T extends Comparable<T>> implements Searcher {
    PriorityQueue<State<T>> openList = new PriorityQueue<>();

    private int evaluatedNodes = 0;

    abstract Solution searchAlgorithm(Searchable<T> s);

    void addNode() {
        evaluatedNodes++;
    }

    @Override
    public Solution search(Searchable s) {
        long startTime = System.currentTimeMillis();
        System.out.println("searchAlgorithm started");

        Solution solution = searchAlgorithm(s);

        long stopTime = System.currentTimeMillis();
        double elapsedTime = stopTime - startTime;
        System.out.println("searchAlgorithm total seconds: " + elapsedTime / 1000);
        System.out.println("searchAlgorithm total evaluated nodes: " + evaluatedNodes);
        return solution;
    }

    State<T> popOpenList() {
        addNode();
        return openList.poll();
    }

    void addToOpenList(State<T> state) {
        openList.add(state);
    }

    Solution backtraceSolution(State<T> state) {
        ArrayList<State<T>> list = new ArrayList<>();
        State<T> n = state;
        while (n.getCameFrom() != null) {
            list.add(0, n.getCameFrom());
            n = n.getCameFrom();
        }
        Solution solution = new AlgoSolution<>(list);
        solution.reverse();
        return solution;
    }


//    public Solution backTrace(PuzzleState goalState) {
//        Solution solution = new Solution();
//        PuzzleState currentState = goalState;
//        while (currentState.getCameFrom() != null) {
//            // add step
//            Point posClicked = currentState.getPosClicked();
//            solution.addStep(posClicked.x + "," + posClicked.y + "," + 1);
//            currentState = currentState.getCameFrom();
//        }
//        solution.reverse();
//        return solution;
//    }

}
