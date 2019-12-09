package sample;

import javafx.animation.*;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


public abstract class MovableObject extends ObjectManager {
    //    int i = 0;
//    int j = 1;
    protected boolean existence;
    Group root;
    Path objectPath;
    PathTransition objectAnimation;
    PathTransition hitBoxAnimation;
    PathTransition healthBarAnimation;
    int health;
    double x;
    double y;
    Rectangle hitBox;

    public MovableObject(Group root,Player player) {
        this.player=player;
        this.root = root;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void move() {
    }

    public void onDestroyed() {
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public boolean isExistence() {
        return existence;
    }
}