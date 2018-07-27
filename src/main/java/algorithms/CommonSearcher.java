package algorithms;

import models.State;
import solver.AlgoSolution;
import solver.Solution;

import java.util.ArrayList;
import java.util.PriorityQueue;

abstract class CommonSearcher implements Searcher {
    PriorityQueue<State<?>> openList = new PriorityQueue<>();

    private int evaluatedNodes = 0;

    abstract Solution searchAlgorithm(Searchable s);

    void addNode() {
        evaluatedNodes++;
    }

    @Override
    public Solution search(Searchable s) {
        long startTime = System.currentTimeMillis();
//        System.out.println("searchAlgorithm started");

        Solution solution = searchAlgorithm(s);

        long stopTime = System.currentTimeMillis();
        double elapsedTime = stopTime - startTime;
//        System.out.println("searchAlgorithm total seconds: " + elapsedTime / 1000);
//        System.out.println("searchAlgorithm total evaluated nodes: " + evaluatedNodes);
        return solution;
    }

    State<?> popOpenList() {
        addNode();
        return openList.poll();
    }

    void addToOpenList(State<?> state) {
        openList.add(state);
    }

    Solution backtraceSolution(State<?> state) {
        ArrayList<State<?>> list = new ArrayList<>();
        State<?> n = state;
        list.add(n);
        while (n.getCameFrom() != null) {
            list.add(n.getCameFrom());
            n = n.getCameFrom();
        }
        Solution solution = new AlgoSolution(list);
        solution.reverse();
        return solution;
    }

}
