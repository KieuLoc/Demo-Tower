package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

abstract  public class GameObject {
    int x;
    int y;
    Image image;
    abstract void render(GraphicsContext gc);
    abstract void update();
}
