package solver;

import models.PuzzleStep;
import models.State;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class PipesPuzzleSolution implements Serializable {
    private ArrayList<PuzzleStep> steps = new ArrayList<>();

    public PipesPuzzleSolution(Solution solution) {
        //Move to PipesPuzzleSolution
        List<State> states = solution.getStateList();

        List<PuzzleStep> justSteps = new ArrayList<>();
        State<?> current = states.get(states.size() - 1);
        while(current.getCameFrom() != null) {
            justSteps.add((PuzzleStep) current.getStep());
            current = current.getCameFrom();
        }

        Map<Point, Long> collect = justSteps.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(PuzzleStep::getPoint, Collectors.counting()));
        for (Point point : collect.keySet()) {
            steps.add(new PuzzleStep(point, collect.get(point).intValue()%4));
        }
    }

    public ArrayList<PuzzleStep> getSteps() {
        return steps;
    }

    public void printSteps(){
        for (PuzzleStep step:steps) {
            System.out.println(step);
        }
    }
}
