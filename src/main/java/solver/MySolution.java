package solver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MySolution implements Solution, Serializable {
    private char[][] finalSolution;
    private String levelString;
    private int numOfCols;
    private int numOfRows;
    private List<Step> steps = new ArrayList<>();

    public MySolution(String levelString, int numOfRows, int numOfCols) {
        //Move to solution
        this.levelString = levelString;
        finalSolution = new char[numOfRows][numOfCols];
        for (int i = 0; i < numOfRows; i++) {
            finalSolution[i] = new char[numOfCols];
            finalSolution[i] = levelString.substring(i * numOfCols, (i * numOfCols) + numOfCols).toCharArray();
        }
        this.numOfCols = numOfCols;
        this.numOfRows = numOfRows;
    }

    public MySolution() {
        this("", 0, 0);
    }

    @Override
    public String getLevelString() {
        return levelString;
    }

    @Override
    public void printFinalBoard() {
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfCols; j++) {
                System.out.print(finalSolution[i][j]);
            }
        }
    }

    @Override
    public void printSteps() {
        for (Step step : steps) {
            System.out.println(step.toString());
        }
        System.out.println();
    }


}
