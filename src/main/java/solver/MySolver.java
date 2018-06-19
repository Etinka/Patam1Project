package solver;

import algorithms.BestFirstSearch;
import algorithms.PipesPuzzle;
import models.State;

public class MySolver implements Solver {

    @Override
    public Solution solve(String level, int rows, int cols) {
        PipesPuzzle pipesPuzzle = new PipesPuzzle(new State<>(level), rows, cols);
        Solution<String> solution = new BestFirstSearch<String>().search(pipesPuzzle);
        return solution;
    }
}
