package solver;

import models.State;
import models.Step;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PipesPuzzleSolution implements Serializable {
    private ArrayList<Step> steps = new ArrayList<>();

    public PipesPuzzleSolution(Solution solution) {
        //Move to PipesPuzzleSolution
        List<State> states = solution.getSteps();

        List<Step> justSteps = new ArrayList<>();
        State<?> current = states.get(states.size() - 1);
        while(current.getCameFrom() != null) {
            justSteps.add(current.getStep());
            current = current.getCameFrom();
        }

        Map<Point, Long> collect = justSteps.stream()
                .collect(Collectors.groupingBy(Step::getPoint, Collectors.counting()));
        for (Point point : collect.keySet()) {
            steps.add(new Step(point, collect.get(point).intValue()));
        }
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void printSteps(){
        for (Step step:steps) {
            System.out.println(step);
        }
    }
}
