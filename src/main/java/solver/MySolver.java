package solver;

import algorithms.BFS;
import algorithms.BestFirstSearch;
import algorithms.DFS;
import algorithms.PipesPuzzle;
import models.PuzzleState;

public class MySolver implements Solver {

    @Override
    public Solution solve(char[][] level, int rows, int cols) {
        PipesPuzzle pipesPuzzle = new PipesPuzzle(new PuzzleState(level, rows), rows, cols);
        //Solution solution = new BestFirstSearch<String>().search(pipesPuzzle);
//        Solution solution = new BFS<String>().search(pipesPuzzle);
        Solution solution = new DFS<String>().search(pipesPuzzle);
        return solution;
    }
}
