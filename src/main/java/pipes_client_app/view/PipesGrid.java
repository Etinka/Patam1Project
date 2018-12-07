package pipes_client_app.view;


import algorithms.PipesPuzzle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PipesGrid extends Canvas {

    private char[][] mazeData;

    private StringProperty regularPipeImage;
    private StringProperty anglePipeImage;
    private double colWidth;
    private double rowHeight;

    private Image pipeImageVertical, pipeImageHorizontal, pipeImage0Rotation, pipeImage90Rotation, pipeImage180Rotation, pipeImage270Rotation;


    public StringProperty regularPipeImageProperty() {
        return regularPipeImage;
    }

    public void setRegularPipeImage(String regularPipeImage) {
        this.regularPipeImage.set(regularPipeImage);
    }

    /**
     * Returns the straight pipe image the user of the view set.
     * If no image was set - a default will be used.
     */
    public String getRegularPipeImage() {
        if (regularPipeImage.get() == null || regularPipeImage.get().isEmpty()) {
            return "./resources/straight-cartoon-pipe.jpg";
        }
        return regularPipeImage.get();
    }

    public StringProperty anglePipeImageProperty() {
        return anglePipeImage;
    }

    public void setAnglePipeImage(String anglePipeImage) {
        this.anglePipeImage.set(anglePipeImage);
    }

    /**
     * Returns the angled pipe image the user of the view set.
     * If no image was set - a default will be used.
     */
    public String getAnglePipeImage() {
        if (anglePipeImage.get() == null || anglePipeImage.get().isEmpty()) {
            return "./resources/cartoon-pipe.jpg";
        }
        return anglePipeImage.get();
    }

    public PipesGrid() {
        regularPipeImage = new SimpleStringProperty();
        anglePipeImage = new SimpleStringProperty();
        addEventFilter(MouseEvent.MOUSE_CLICKED, (this::clickedOnPosition));
    }

    private void clickedOnPosition(MouseEvent event) {
        int col = (int) (event.getX() / colWidth);
        int row = (int) (event.getY() / rowHeight);
//        System.out.println("clickedOnPosition: " + row + ", " + col);
        mazeData[row][col] = PipesPuzzle.getNextChar(mazeData[row][col]);
        redraw();
    }

    public void setMazeData(char[][] mazeData) {
        this.mazeData = mazeData;
        initImages();
        redrawMaze();
    }

    private void redrawMaze() {
        double width = getWidth();
        double height = getHeight();
        colWidth = width / mazeData[0].length;
        rowHeight = height / mazeData.length;
        redraw();
    }

    private void initImages() {
        Image regularPipe = null;
        Image anglePipe = null;
        try {
            regularPipe = new Image(new FileInputStream(getRegularPipeImage()));
            anglePipe = new Image(new FileInputStream(getAnglePipeImage()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        int rotateImage = 0;
        //'F'
        ImageView iv = new ImageView(anglePipe);
        iv.setRotate(rotateImage);
        pipeImage0Rotation = iv.snapshot(params, null);
        //'7'
        rotateImage = 90;
        iv.setRotate(rotateImage);
        pipeImage90Rotation = iv.snapshot(params, null);
        //'J'
        rotateImage = 180;
        iv.setRotate(rotateImage);
        pipeImage180Rotation = iv.snapshot(params, null);
        //'L'
        rotateImage = 270;
        iv.setRotate(rotateImage);
        pipeImage270Rotation = iv.snapshot(params, null);

        //'|'
        rotateImage = 0;
        iv = new ImageView(regularPipe);
        iv.setRotate(rotateImage);
        pipeImageVertical = iv.snapshot(params, null);
        //'-'
        rotateImage = 90;
        iv.setRotate(rotateImage);
        pipeImageHorizontal = iv.snapshot(params, null);
    }

    private void redraw() {
        if (mazeData != null) {
            GraphicsContext graphicsContext = getGraphicsContext2D();
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            for (int i = 0; i < mazeData.length; i++) {
                for (int j = 0; j < mazeData[i].length; j++) {
                    Image image = null;
                    switch (mazeData[i][j]) {
                        case '|':
                            image = pipeImageVertical;
                            break;
                        case '-':
                            image = pipeImageHorizontal;
                            break;
                        case 'F':
                            image = pipeImage0Rotation;
                            break;
                        case '7':
                            image = pipeImage90Rotation;
                            break;
                        case 'J':
                            image = pipeImage180Rotation;
                            break;
                        case 'L':
                            image = pipeImage270Rotation;
                            break;
                        case ' ':
                            break;
                        case 'g':
                            graphicsContext.setFill(Color.BLUE);
                            break;
                        case 's':
                            graphicsContext.setFill(Color.YELLOW);
                            break;
                    }
                    graphicsContext.clearRect(j * colWidth, i * rowHeight, colWidth, rowHeight);

                    if (image == null) {
                        graphicsContext.fillRect(j * colWidth, i * rowHeight, colWidth, rowHeight);
                        //This can be removed once there are images for start and goal
                    } else {
                        graphicsContext.drawImage(image, j * colWidth, i * rowHeight, colWidth, rowHeight);
                    }
                }
            }
        }
    }

    @Override
    public void resize(double width, double height) {
        super.setWidth(width);
        super.setHeight(height);
        redrawMaze();
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double minHeight(double width) {
        return 64;
    }

    @Override
    public double maxHeight(double width) {
        return 1000;
    }

    @Override
    public double prefHeight(double width) {
        return minHeight(width);
    }

    @Override
    public double minWidth(double height) {
        return 0;
    }

    @Override
    public double maxWidth(double height) {
        return 10000;
    }
}
