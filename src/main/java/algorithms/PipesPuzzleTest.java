package algorithms;

import models.State;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PipesPuzzleTest {

    @Before
    public void setUp() {

    }

    @Test
    public void isGoalWithoutSolution() {
        String board = "s-------g";
        State<String> state = new State<>(board);
        PipesPuzzle pipesPuzzle = new PipesPuzzle(state, 3, 3);
        assertFalse(pipesPuzzle.isGoal(state));
    }

    @Test
    public void isGoalWithSolution() {
        String board = "s7--|--Lg";
        State<String> state = new State<>(board);
        PipesPuzzle pipesPuzzle = new PipesPuzzle(state, 3, 3);
        assertTrue(pipesPuzzle.isGoal(state));
    }

    @Test
    public void isGoalWithSInTheMiddleSolution() {
        String board = "-|||s7g-J";
        State<String> state = new State<>(board);
        PipesPuzzle pipesPuzzle = new PipesPuzzle(state, 3, 3);
        assertTrue(pipesPuzzle.isGoal(state));
    }

    @Test
    public void isGoalWithCircleInTheMiddleSolution() {
        String board = "s7|LJ7g-J";
        State<String> state = new State<>(board);
        PipesPuzzle pipesPuzzle = new PipesPuzzle(state, 3, 3);
        assertTrue(pipesPuzzle.isGoal(state));
    }


    @Test
    public void possibleStates() {
        String board = "s7|LJ7g-J";
        State<String> state = new State<>(board);
        PipesPuzzle pipesPuzzle = new PipesPuzzle(state, 3, 3);
        ArrayList list = (pipesPuzzle.getAllPossibleStates(state));
        assertTrue(pipesPuzzle.isGoal(state));
    }

    @Test
    public void getAllPossibleStates() {
    }
}