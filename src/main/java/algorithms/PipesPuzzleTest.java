package algorithms;

import models.PuzzleState;
import models.State;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PipesPuzzleTest {

    int numRows = 3;
    int numCols = 3;

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
        PipesPuzzle pipesPuzzle = new PipesPuzzle(state, 3, 3);
        assertTrue(pipesPuzzle.isGoal(state));
    }

    @Test
    public void isGoalWithSInTheMiddleSolution() {
        String board = "-|||s7g-J";
        PuzzleState state = new PuzzleState(convertStringToChar(board, numRows, numCols), numRows);
        PipesPuzzle pipesPuzzle = new PipesPuzzle(state, 3, 3);
        assertTrue(pipesPuzzle.isGoal(state));
    }

    @Test
    public void isNotGoalWithOnePathInTheMiddleSolution() {
        String board = "s-7F-JL|g";
        PuzzleState state = new PuzzleState(convertStringToChar(board, numRows, numCols), numRows);
        PipesPuzzle pipesPuzzle = new PipesPuzzle(state, 3, 3);
        assertFalse(pipesPuzzle.isGoal(state));
    }

    @Test
    public void isGoalWithOnePathInTheMiddleSolution() {
        String board = "s-7F-JL-g";
        PuzzleState state = new PuzzleState(convertStringToChar(board, numRows, numCols), numRows);
        PipesPuzzle pipesPuzzle = new PipesPuzzle(state, 3, 3);
        assertTrue(pipesPuzzle.isGoal(state));
    }

    @Test //todo the circle is not working
    public void isGoalWithCircleInTheMiddleSolution() {
        String board = "s7|LJ7g-J";
        PuzzleState state = new PuzzleState(convertStringToChar(board, numRows, numCols), numRows);
        PipesPuzzle pipesPuzzle = new PipesPuzzle(state, 3, 3);
        assertTrue(pipesPuzzle.isGoal(state));
    }


    @Test
    public void possibleStates() {
        String board = "s|LF|JL|g";
//        String board = "s7|LJ7g-J";
        PuzzleState state = new PuzzleState(convertStringToChar(board, numRows, numCols), numRows);
        PipesPuzzle pipesPuzzle = new PipesPuzzle(state, 3, 3);
        state.printState();
        System.out.println("*********");
        ArrayList<State<char[][]>> list = pipesPuzzle.getAllPossibleStates(state);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).printState();
            System.out.println();
        }
        assertEquals(7, list.size());
    }

    @Test
    public void getAllPossibleStates() {
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