package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Tower {
    Image image;
    ImageView imageView;
    double x;
    double y;
    double w;
    double h;
    double r;
    double dx;
    double dy;
    double dr;

    double dame;
    double blood;

    Pane layer;

    public Tower(Pane layer, Image image, double x, double y, double r, double dx, double dy, double dr, double blood, double damage) {

        this.layer = layer;
        this.image = image;
        this.x = x;
        this.y = y;
        this.r = r;
        this.dx = dx;
        this.dy = dy;
        this.dr = dr;

        this.blood = blood;
        this.dame = dame;

        this.image = image;
        this.imageView= new ImageView(image);
        this.imageView.relocate(x, y);
        this.imageView.setRotate(r);
        this.w = image.getWidth();
        this.h = image.getHeight();
        addToLayer();
    }

    public Tower() {

    }

    private void addToLayer() {
        this.layer.getChildren().add(this.imageView);
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getH() {
        return h;
    }

    public double getW() {
        return w;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getR() {
        return r;
    }

    public double getDr() {
        return dr;
    }

    public void setDr(double dr) {
        this.dr = dr;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getBlood() {
        return blood;
    }

    public double getDame() {
        return dame;
    }

    public void setDame(double dame) {
        this.dame = dame;
    }

    public void setBlood(double blood) {
        this.blood = blood;
    }

    public Pane getLayer() {
        return layer;
    }

    public void setLayer(Pane layer) {
        this.layer = layer;
    }

    public Image getImage() {
        return image;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public double getCenterX() {
        return x + w * 0.5;
    }

    public double getCenterY() {
        return y + h * 0.5;
    }
    public void UI(){
        imageView.relocate(x,y);
        imageView.setRotate(r);
    }
    public void hurt(TowerObject otherTower){
        blood -=otherTower.getDame();
    }

    public void render(GraphicsContext gc) {
    }
}
