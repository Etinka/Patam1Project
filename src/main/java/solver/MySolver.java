package solver;

import algorithms.*;
import models.PuzzleState;

public class MySolver implements Solver {

    @Override
    public Solution solve(char[][] level, int rows, int cols) {
        PipesPuzzle pipesPuzzle = new PipesPuzzle(new PuzzleState(level, rows), rows, cols);
        Solution solution = new BestFirstSearch().search(pipesPuzzle);
//        Solution solution = new BFS<String>().search(pipesPuzzle);
//        Solution solution = new DFS().search(pipesPuzzle);
//        Solution solution = new HillClimbing().search(pipesPuzzle);
        return solution;
    }
}
