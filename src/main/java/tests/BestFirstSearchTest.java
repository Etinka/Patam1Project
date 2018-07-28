package tests;

import algorithms.BestFirstSearch;
import algorithms.PipesPuzzle;
import models.PuzzleState;
import models.PuzzleStep;
import models.State;
import org.junit.Assert;
import org.junit.Test;
import solver.Solution;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static tests.TestUtils.convertStringToChar;

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
        Solution solution = new BestFirstSearch().search(pipesPuzzle);
        addingCounters(solution);

        assertEquals(6, solution.getStateList().size());
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
        addingCounters(solution);

        assertEquals(6, solution.getStateList().size());
    }

    private void addingCounters(Solution solution) {
        int[][] counters = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                counters[i][j] = 0;
            }
        }
        System.out.println("Final solution:");

        List<State> states = solution.getStateList();

       /* for (State state : states) {
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
*/
        System.out.println("Counters:");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(counters[i][j] + "_");
            }
            System.out.println();
        }
    }


    @Test
    public void duplicates() throws Exception {

        List<PuzzleStep> items = new ArrayList<>();
        items.add(new PuzzleStep(1, 2, 1));
        items.add(new PuzzleStep(1, 2, 1));
        items.add(new PuzzleStep(0, 2, 1));
        items.add(new PuzzleStep(1, 2, 1));


        Map<PuzzleStep, Long> result = new HashMap<>();


        Map <Point, Long> collect = items.stream()
                .collect(Collectors.groupingBy(PuzzleStep::getPoint, Collectors.counting()));
        items.clear();
        for (Point point: collect.keySet()) {
            items.add(new PuzzleStep(point, collect.get(point).intValue()));

        }
     /*   final Map<String, String> mostFrequentCities =
                items.stream()
                        .collect(Collectors.groupingBy(
                                PuzzleStep::getRowNumString,
//                                Collectors.collectingAndThen(
                                        Collectors.groupingBy(PuzzleStep::getRowNumString, Collectors.counting())
//                                        map -> map.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey()
//                                )
                        ));
*/
//        List<Integer> collect =
//                items.stream()
//                        .collect(Collectors.groupingBy(
//                                PuzzleStep::getRowNum,
//                                Collectors.mapping(PuzzleStep::getRowNum, Collectors.toList()))
//                        );
        //= items.stream()
//                .collect(Collectors.groupingBy(str -> str, counting()));
//                        Function.identity(), Collectors.counting()));
// Collections.frequency(list, new Student("Ram", 19)));
//        items.stream().map(PuzzleStep::getEmpName).filter(emId.getEmpName()::equals).count();



        Assert.assertEquals(3, Collections.frequency(items, new PuzzleStep(1, 2, 1)));
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