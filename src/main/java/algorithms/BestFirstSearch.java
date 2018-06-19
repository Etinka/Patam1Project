package algorithms;

import models.State;
import solver.MySolution;
import solver.Solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class BestFirstSearch<T> extends BaseAlgorithm<T> {

    @Override
    public Solution<T> search(Searchable<T> s) {
        startSearch();
        //Initializing
        PriorityQueue<State<T>> openList = new PriorityQueue<>();
        HashSet<State<T>> closedSet = new HashSet<>();
        openList.add(s.getInitialState());

        while (openList.size() > 0) {
            State<T> n = openList.poll();
            closedSet.add(n);

            if (s.isGoal(n)) {
                return backtraceSolution(n);
            }
            ArrayList<State<T>> successors = s.getAllPossibleStates(n);
            for (State<T> state : successors) {
                if (!closedSet.contains(state) && !openList.contains(state)) {
                    state.setCameFrom(n);
                    openList.add(state);
                }
                //TODO do we need "else"???
            }

        }

        endSearch();
        return null;//TODO change
    }

    Solution<T> backtraceSolution(State<T> state) {
        ArrayList<State<T>> list = new ArrayList<>();
        State<T> n = state;
        while (n.getCameFrom() != null) {
            list.add(0, n.getCameFrom());
            n = n.getCameFrom();
        }

        return new MySolution(list);
        //TODO replace null
    }


}