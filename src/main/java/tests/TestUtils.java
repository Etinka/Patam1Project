package tests;

import models.PuzzleState;
import models.State;
import solver.AlgoSolution;
import solver.Solution;

import java.util.ArrayList;
import java.util.List;

class TestUtils {

    static Solution createSolution() {
        char[][] table =
                        {{'s', '|', 'L'},
                        {'F', '|', 'J'},
                        {'L', '|', 'g'}};
        char[][] table2 =
                        {{'s', '|', 'J'},
                        {'F', '|', 'J'},
                        {'L', '|', 'g'}};
        char[][] table3 =
                        {{'s', '|', '7'},
                        {'F', '|', 'J'},
                        {'L', '|', 'g'}};
        char[][] table4 =
                        {{'s', '|', '7'},
                        {'F', '-', 'J'},
                        {'L', '|', 'g'}};
        PuzzleState puzzleState = new PuzzleState(table, 3);
        PuzzleState puzzleState2 = new PuzzleState(table2, 3);
        puzzleState2.setCameFrom(puzzleState);
        PuzzleState puzzleState3 = new PuzzleState(table3, 3);
        puzzleState3.setCameFrom(puzzleState2);
        PuzzleState puzzleState4 = new PuzzleState(table4, 3);
        puzzleState4.setCameFrom(puzzleState3);

        List<State<?>> states = new ArrayList<>();
        states.add(puzzleState2);
        states.add(puzzleState3);
        states.add(puzzleState4);


        return new AlgoSolution(states, puzzleState, puzzleState2);
    }

    static char[][] convertStringToChar(String levelString, int rowNum, int colNum) {
        char[][] level = new char[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            level[i] = new char[colNum];
            level[i] = levelString.substring(i * colNum, (i * colNum) + colNum).toCharArray();
        }
        return level;
    }

}
