package models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;

import java.awt.*;

public class PipesGameModel {
    public BooleanProperty isGoal;
    public IntegerProperty numberOfSteps;
    public ListProperty<char[]> gameBoard;

}
