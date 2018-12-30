package pipes_client_app.view;

import javafx.beans.property.*;
import models.PipesGameModel;

public class PipesViewModel {
    private BooleanProperty isGoal;
    private IntegerProperty numberOfSteps;
    private ListProperty<char[]> gameBoard;
    private PipesGameModel pipesModel;

    public PipesViewModel(PipesGameModel pipesModel){
        this.pipesModel = pipesModel;

        this.isGoal = new SimpleBooleanProperty();
//        this.isGoal.bind(this.pipesModel.isGoal);

        this.numberOfSteps = new SimpleIntegerProperty();
        this.numberOfSteps.bind(this.pipesModel.stepsNum);

        this.gameBoard = new SimpleListProperty<>();
        this.gameBoard.bind(this.pipesModel.gameBoard);


    }
}
