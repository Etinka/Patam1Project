package algorithms;

import models.PuzzleState;
import models.State;
import org.junit.Test;
import solver.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

import static org.junit.Assert.*;

public class BestFirstSearchTest {

    @Test
    public void searchAlgorithm() {
        String board = "s|LF|JL|g";//s-7F-J
        /*

        s-7
        F-J
        L-g

        * */
//        String board = "s-7F-JL|g";//works

//        String board = "sL7g-7";
//        String board = "s|7-g7";
        //solution = "s-7-gJ

        int numRows = 3;
        int numCols = 3;
        PipesPuzzle pipesPuzzle = new PipesPuzzle(new PuzzleState(convertStringToChar(board, numRows, numCols),
                numRows), numRows, numCols);
        Solution solution = new BestFirstSearch<String>().search(pipesPuzzle);
        ArrayList<State> states = solution.getSteps();
        addingCounters(solution);
       /* for (State state : states
                ) {
            if (state.getStep() != null) {
                System.out.println(state.getStep());
            }
        }*/
        assertEquals(3, solution.getSteps().size());
    }

    private void addingCounters(Solution solution) {
        int[][] counters = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                counters[i][j] = 0;
            }
        }

        ArrayList<State> states = solution.getSteps();
        for (State state : states) {
            if (state.getStep() != null) {
                counters[state.getStep().getRowNum()][state.getStep().getColNum()]++;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(counters[i][j]%3 + "_");
            }
            System.out.println();
        }
    }


    @Test
    public void testEquals() {
        String board = "s|LF|JL|g";
        String board2 = "s|7F|JL|g";
        PriorityQueue<PuzzleState> openList = new PriorityQueue<>();
        int numRows = 3;
        int numCols = 3;
        PuzzleState state = new PuzzleState(convertStringToChar(board, numRows, numCols),
                numRows);
        PuzzleState state2 = new PuzzleState(convertStringToChar(board, numRows, numCols),
                numRows);
        PuzzleState stat32 = new PuzzleState(convertStringToChar(board2, numRows, numCols),
                numRows);

        openList.add(new PuzzleState(convertStringToChar(board, numRows, numCols),
                numRows));
        assertTrue(state.equals(state2));
        assertFalse(state.equals(stat32));
        assertTrue(openList.contains(state));
    }

    private char[][] convertStringToChar(String levelString, int rowNum, int colNum) {
        char[][] level = new char[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            level[i] = new char[colNum];
            level[i] = levelString.substring(i * colNum, (i * colNum) + colNum).toCharArray();
        }
        return level;
    }
}