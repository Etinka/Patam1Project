package pipes_client_app.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    char [][] mazeData = {
            {'s', 'L', 'F', '-', 'J', '7', '7', '7' , '7'},
            {'7', '7', '7', '7', '7', '7', '7', '7' , '7'},
            {'7', '7', '7', '7', '7', '7', '7', '7' , '7'},
            {'7', '7', '7', '7', '|', '7', '7', '7' , '7'},
            {'7', '7', '7', '7', 'L', '7', '7', '7' , '7'},
            {'7', '7', '7', '7', '7', '7', '7', '7' , '7'},
            {'7', '7', '7', '7', '|', '7', '7', '7' , '7'},
            {'7', '7', '-', '-', '-', '-', '-', '-' , 'g'},
    };

    @FXML
    private PipesGrid pipesGrid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pipesGrid.setMazeData(mazeData);
    }

    public void start() {
        System.out.println("start");
    }

    public void openFile() {
        System.out.println("openFile");
        FileChooser fc = new FileChooser();
        fc.setTitle("Open maze file");
        fc.setInitialDirectory(new File("./resources"));
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Xml files", "xml"));
        File chosen = fc.showOpenDialog(null);
        if (chosen != null) {
            System.out.println("Chose: " + chosen.getName());
        }
    }

}
