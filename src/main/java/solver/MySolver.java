package solver;

import algorithms.BestFirstSearch;
import algorithms.PipesPuzzle;
import models.PuzzleState;

public class MySolver implements Solver {

    @Override
    public Solution solve(char[][] level, int rows, int cols) {
        PipesPuzzle pipesPuzzle = new PipesPuzzle(new PuzzleState(level, rows), rows, cols);
        Solution solution = new BestFirstSearch<String>().search(pipesPuzzle);
        return solution;
    }
}
