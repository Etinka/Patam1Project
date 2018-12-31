package pipes_client_app.view;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import models.PipesGameModel;
import pipes_client_app.dialogs.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    private MediaPlayer mediaPlayer;
    private PipesGameModel pipesGameModel;

    @FXML
    private PipesGrid pipesGrid;
    @FXML
    private Label stepsLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label serverLabel;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button solveButton;


    private NakedObjectDisplayer nakedObjectDisplayer = new NakedObjectDisplayer();
    private ServerConfigObject serverConfigObject = new ServerConfigObject();
    private ThemeConfigObject themeConfigObject = new ThemeConfigObject();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pipesGameModel = new PipesGameModel();

        changeTheme();

        pipesGrid.init(pipesGameModel.getMazeData(), rowCol -> {
            if (pipesGameModel.isGameStarted()) {
                pipesGameModel.movePipe(rowCol.getRow(), rowCol.getCol());
                pipesGrid.redraw();
            } else {
                nakedObjectDisplayer.display(new DialogObject("Error!", "Please click on Start to begin playing", "Ok"));
            }
            return null;
        });

        pipesGameModel.gameBoard.addListener((observable, oldValue, newValue) -> pipesGrid.setMazeData(pipesGameModel.gameBoard.toArray(new char[pipesGameModel.gameBoard.size()][])));
        pipesGameModel.stepsNum.addListener((observable, oldValue, newValue) -> this.stepsLabel.setText("Steps taken: " + Integer.toString(pipesGameModel.stepsNum.get())));
        pipesGameModel.secondsPassed.addListener((observable, oldValue, newValue) -> this.timeLabel.setText("Seconds passed: " + Integer.toString(pipesGameModel.secondsPassed.get())));
        pipesGameModel.isGoal.addListener((observable, oldValue, newValue) -> {
            if (newValue)
                Platform.runLater(() -> nakedObjectDisplayer.display(new DialogObject("YAY!!!", "You solved the game!", "Woohoo!")));
        });
    }

    public void start() {
        pipesGameModel.start();
        toggleButtons(true);
    }

    public void stop() {
        pipesGameModel.stop();
        toggleButtons(false);
    }

    public void solve() {
        stop();
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    System.out.println("Solve clicked " + serverConfigObject.getIP() + " " + serverConfigObject.getPort());
                    Platform.runLater(() -> serverLabel.setText("Server Status: Connecting to " + serverConfigObject.getIP() + ":" + serverConfigObject.getPort()));
                    pipesGameModel.connect(serverConfigObject.getIP(), serverConfigObject.getPort());
                    Platform.runLater(() -> serverLabel.setText("Server Status: Waiting for solution"));
                    pipesGameModel.solve();
                    pipesGameModel.disconnect();
                    Platform.runLater(() -> serverLabel.setText("Server Status: Disconnected"));
                } catch (IOException e) {
                    Platform.runLater(() -> nakedObjectDisplayer.display(new DialogObject("Oops...", "Couldn't connect to the server, please try again later", "Ok")));
                    Platform.runLater(() -> serverLabel.setText("Server Status: Couldn't connect to the server"));
                    e.printStackTrace();
                }
                return null;
            }
        };
        new Thread(task).start();
    }

    private void toggleButtons(boolean startDisabled) {
        startButton.setDisable(startDisabled);
        stopButton.setDisable(!startDisabled);
        solveButton.setDisable(!startDisabled);
    }

    public void openFile() {
        System.out.println("openFile");
        FileChooser fc = new FileChooser();
        fc.setTitle("Load maze file");
        fc.setInitialDirectory(new File("./resources"));
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File chosen = fc.showOpenDialog(null);
        if (chosen != null) {
            System.out.println("Chose: " + chosen.getName());
            try {
                pipesGameModel.loadGame(chosen);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveFile() {
        stop();
        System.out.println("saveFile");
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose the folder to save the current game");
        FileChooser.ExtensionFilter txtExtensionFilter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
        fc.getExtensionFilters().add(txtExtensionFilter);
        fc.setSelectedExtensionFilter(txtExtensionFilter);

        File selectedFile = fc.showSaveDialog(null);

        if (selectedFile == null) {
            return;
        }
        pipesGameModel.saveCurrentGame(selectedFile);
    }

    public void serverConfig() {
        nakedObjectDisplayer.display(this.serverConfigObject);
    }

    public void themeConfig() {
        nakedObjectDisplayer.display(this.themeConfigObject, isChanged -> {
            System.out.println("Is theme changed: " + isChanged);
            if (isChanged) {
                changeTheme();
            }
            return null;
        });
    }

    private void changeTheme() {
        ThemeType themeType = themeConfigObject.getSelectedTheme();
        pipesGrid.setAnglePipeImage(themeType.getAnglePipe());
        pipesGrid.setRegularPipeImage(themeType.getRegularPipe());
        pipesGrid.setBackgroundImageProperty(themeType.getBackgroundImage());
        pipesGrid.setStartImage(themeType.getStartImage());
        pipesGrid.setGoalImage(themeType.getEndImage());
        pipesGrid.initImages();
        pipesGrid.redraw();
        playMusic(themeType.getMusic());
    }

    private void playMusic(String musicFile) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        Media hit = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }


}
