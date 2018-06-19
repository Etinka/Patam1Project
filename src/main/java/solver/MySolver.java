package solver;

import algorithms.BestFirstSearch;
import algorithms.PipesPuzzle;

import java.util.ArrayList;

public class MySolver implements Solver {

    @Override
    public Solution solve(String level) {
        PipesPuzzle pipesPuzzle = new PipesPuzzle(level );
//        Solution<String>solution = new BestFirstSearch<String>().search(level);
        return new MySolution<>("123", new ArrayList<>());
    }
}
