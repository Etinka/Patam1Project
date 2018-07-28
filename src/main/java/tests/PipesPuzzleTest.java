package tests;

import algorithms.PipesPuzzle;
import models.PuzzleState;
import models.State;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static tests.TestUtils.convertStringToChar;

public class PipesPuzzleTest {

    private int numRows = 3;
    private int numCols = 3;

    @Before
    public void setUp() {

    }

    @Test
    public void isGoalWithoutSolution() {
        String board = "s-------g";
        PuzzleState state = new PuzzleState(convertStringToChar(board, numRows, numCols), numRows);
        PipesPuzzle pipesPuzzle = new PipesPuzzle(state, numRows, numCols);
        assertFalse(pipesPuzzle.isGoal(state));
    }

    @Test
    public void isGoalWithSolution() {
        String board = "s7--|--Lg";
        PuzzleState state = new PuzzleState(convertStringToChar(board, numRows, numCols), numRows);
        PipesPuzzle pipesPuzzle = new PipesPuzzle(state, numRows, numCols);
        assertTrue(pipesPuzzle.isGoal(state));
    }

    @Test
    public void isGoalWithSInTheMiddleSolution() {
        String board = "-|||s7g-J";
        PuzzleState state = new PuzzleState(convertStringToChar(board, numRows, numCols), numRows);
        PipesPuzzle pipesPuzzle = new PipesPuzzle(state, numRows, numCols);
        assertTrue(pipesPuzzle.isGoal(state));
    }

    @Test
    public void isNotGoalWithOnePathInTheMiddleSolution() {
        String board = "s-7F-JL|g";
        PuzzleState state = new PuzzleState(convertStringToChar(board, numRows, numCols), numRows);
        PipesPuzzle pipesPuzzle = new PipesPuzzle(state, numRows, numCols);
        assertFalse(pipesPuzzle.isGoal(state));
    }

    @Test
    public void isGoalWithOnePathInTheMiddleSolution() {
        String board = "s-7F-JL-g";
        PuzzleState state = new PuzzleState(convertStringToChar(board, numRows, numCols), numRows);
        PipesPuzzle pipesPuzzle = new PipesPuzzle(state, numRows, numCols);
        assertTrue(pipesPuzzle.isGoal(state));
    }

    @Test
    public void isGoalInTheMiddleSolution() {
        String board = "sL7g-7";
        int rows = 2;
        int cols = 3;
        PuzzleState state = new PuzzleState(convertStringToChar(board, rows, cols), rows);
        PipesPuzzle pipesPuzzle = new PipesPuzzle(state, rows, cols);
        assertTrue(pipesPuzzle.isGoal(state));
    }

  /*  @Test //todo the circle is not working
    public void isGoalWithCircleInTheMiddleSolution() {
        String board = "s7|LJ7g-J";
//        String board = "  s7|
//                          LJ7
//                          g-J";
        PuzzleState state = new PuzzleState(convertStringToChar(board, numRows, numCols), numRows);
        PipesPuzzle pipesPuzzle = new PipesPuzzle(state, numRows, numCols);
        assertTrue(pipesPuzzle.isGoal(state));
    }*/


    @Test
    public void possibleStates() {
//        String board = "s|LF|JL|g";
        String board = "s-LL-LL-g";
//        String board = "s7|LJ7g-J";
        PuzzleState state = new PuzzleState(convertStringToChar(board, numRows, numCols), numRows);
        PipesPuzzle pipesPuzzle = new PipesPuzzle(state, numRows, numCols);
        state.printState();
        System.out.println("*********");
        List<State<char[][]>> list = pipesPuzzle.getAllPossibleStates(state);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).printState();
            System.out.println();
        }
        System.out.println("*********");
        assertEquals(9, list.size());
    }

    @Test
    public void getAllPossibleStates() {
    }

}