package tests;

import algorithms.HillClimbing;
import org.junit.Before;
import org.junit.Test;
import solver.PipesPuzzleSolution;

import static org.junit.Assert.assertEquals;

public class HillClimbingTest {

    private String searcher;

    @Before
    public void setup() {
        searcher = HillClimbing.class.getName();
    }
    
    @Test
    public void testAlgo0() {
        PipesPuzzleSolution solution =
                TestUtils.createPipesPuzzleSolution(searcher, 0);
        solution.printSteps();
        assertEquals(1, solution.getSteps().size());
    }

    @Test
    public void testAlgo1() {
        PipesPuzzleSolution solution =
                TestUtils.createPipesPuzzleSolution(searcher, 1);
        solution.printSteps();
        assertEquals(9, solution.getSteps().size());
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
}