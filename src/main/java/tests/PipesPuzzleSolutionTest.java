package tests;

import org.junit.Test;
import solver.PipesPuzzleSolution;
import solver.Solution;

import static org.junit.Assert.assertEquals;

public class PipesPuzzleSolutionTest {

    @Test
    public void testConstructor() {
        Solution solution = TestUtils.createSolution();

        PipesPuzzleSolution pipesPuzzleSolution = new PipesPuzzleSolution(solution);
        pipesPuzzleSolution.printSteps();
        assertEquals(2, pipesPuzzleSolution.getSteps().size());
        assertEquals("[1,1,1, 0,2,2]", pipesPuzzleSolution.getSteps().toString());
    }

}