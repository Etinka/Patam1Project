package tests;

import algorithms.BestFirstSearch;
import algorithms.PipesPuzzle;
import models.PuzzleState;
import org.junit.Before;
import org.junit.Test;
import solver.PipesPuzzleSolution;
import solver.Solution;

import java.util.PriorityQueue;

import static org.junit.Assert.*;
import static tests.TestUtils.convertStringToChar;

public class BestFirstSearchTest {
   private String searcher;

    @Before
    public void setup() {
        searcher = BestFirstSearch.class.getName();
    }

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
        Solution solution = new BestFirstSearch().search(pipesPuzzle);

        assertEquals(6, solution.getStateList().size());
    }

    @Test
    public void testAlgo0() {
        PipesPuzzleSolution solution =
                TestUtils.createPipesPuzzleSolution(BestFirstSearch.class.getName(), 0);
        solution.printSteps();
        assertEquals(1, solution.getSteps().size());
    }

    @Test
    public void testAlgo1() {
        PipesPuzzleSolution solution =
                TestUtils.createPipesPuzzleSolution(searcher, 1);
        solution.printSteps();
        assertEquals(8, solution.getSteps().size());
    }

    @Test
    public void testAlgo2() {
        PipesPuzzleSolution solution =
                TestUtils.createPipesPuzzleSolution(searcher, 2);
        solution.printSteps();
        assertEquals(1, solution.getSteps().size());
    }

    @Test
    public void testAlgo3() {
        PipesPuzzleSolution solution =
                TestUtils.createPipesPuzzleSolution(searcher, 3);
        solution.printSteps();
        assertEquals(8, solution.getSteps().size());
    }

    @Test
    public void testAlgo4() {
        PipesPuzzleSolution solution =
                TestUtils.createPipesPuzzleSolution(searcher, 4);
        solution.printSteps();
        assertEquals(1, solution.getSteps().size());
    }

    @Test
    public void testAlgo5() {
        PipesPuzzleSolution solution =
                TestUtils.createPipesPuzzleSolution(searcher, 5);
        solution.printSteps();
        assertEquals(1, solution.getSteps().size());
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
        Solution solution = new BestFirstSearch().search(pipesPuzzle);

        assertEquals(1, solution.getStateList().size());
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
}