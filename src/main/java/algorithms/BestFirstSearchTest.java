package algorithms;

import models.PuzzleState;
import models.State;
import org.junit.Test;
import solver.Solution;

import java.util.ArrayList;
import java.util.PriorityQueue;

import static org.junit.Assert.*;

public class BestFirstSearchTest {

    @Test
    public void searchAlgorithmTest() {
        String board = "s|LF|JL|g";//s-7F-J
        /*
        initial:
        s|L
        F|J
        L|g

        Result:
        s-7
        F-J
        L-g
        * */


        int numRows = 3;
        int numCols = 3;
        PipesPuzzle pipesPuzzle = new PipesPuzzle(new PuzzleState(convertStringToChar(board, numRows, numCols),
                numRows), numRows, numCols);
        Solution solution = new BestFirstSearch<String>().search(pipesPuzzle);
        addingCounters(solution);

        assertEquals(6, solution.getSteps().size());
    }

    //TODO this does not work because of s+g attached
    @Test
    public void searchAlgorithmTest2() {
        String board = "s|Lg|JL|-";
        /*
        initial:
        s|L
        g|J
        L|-

        Result:
        s-7
        g-J
        L|-
        * */


        int numRows = 3;
        int numCols = 3;
        PipesPuzzle pipesPuzzle = new PipesPuzzle(new PuzzleState(convertStringToChar(board, numRows, numCols),
                numRows), numRows, numCols);
        Solution solution = new BestFirstSearch<String>().search(pipesPuzzle);
        addingCounters(solution);

        assertEquals(6, solution.getSteps().size());
    }

    private void addingCounters(Solution solution) {
        int[][] counters = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                counters[i][j] = 0;
            }
        }
        System.out.println("Final solution:");

        ArrayList<State> states = solution.getSteps();

        for (State state : states) {
            if (state.getStep() != null) {
                System.out.println(state.getStep());
                counters[state.getStep().getRowNum()][state.getStep().getColNum()]++;
            }
        }

        for (State state : states) {
            if (state.getStep() != null) {
                counters[state.getStep().getRowNum()][state.getStep().getColNum()]++;
            }
        }

        System.out.println("Counters:");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(counters[i][j] + "_");
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