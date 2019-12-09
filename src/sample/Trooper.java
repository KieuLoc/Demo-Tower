package sample;

import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class Trooper extends MovableObject {
    private int speed = 30;
    private Image bodyImg = new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/towerDefense_tile245.png");
    private ImageView iv = new ImageView(bodyImg);
    private Path objectPath;
    private Path healthBarPath;
    private PathTransition objectAnimation;
    private PathTransition hitBoxAnimation;
    private PathTransition healthBarAnimation;
    private ProgressBar healthBar;
    private int health=50;
    private int cost=20;

    public double getX() {
        x = iv.getTranslateX();
        return x;
    }

    public double getY() {
        y = iv.getTranslateY();
        return y;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        healthBar.setPrefWidth(health*25/200);
        this.health = health;
    }

    public Trooper(Group root,Player player) {
        super(root,player);
        iv.setTranslateX(-1000);
        iv.setTranslateY(-1000);
        root.getChildren().add(iv);
        iv.setVisible(false);
        existence = true;
        hitBox = new Rectangle();
        hitBox.setWidth(12);
        hitBox.setHeight(12);
        hitBox.setFill(Color.TRANSPARENT);
        hitBox.setTranslateX(-1000);
        hitBox.setTranslateY(-1000);
        root.getChildren().add(hitBox);
        healthBar=new ProgressBar(health);
        healthBar.setTranslateX(-1000);
        healthBar.setTranslateY(-1000);
        healthBar.setPrefHeight(9);
        healthBar.setPrefWidth(25);
        healthBar.setStyle("-fx-accent: red;");
        root.getChildren().add(healthBar);
    }

    public void move() {
        iv.setVisible(true);
        hitBox.setVisible(true);
        iv.setFitHeight(32);
        iv.setFitWidth(32);
        objectPath = new Path();
        objectPath.getElements().add(new MoveTo(32 * -2, 32 * 2));
        objectPath.getElements().add(new HLineTo(32 * 4));
        objectPath.getElements().add(new VLineTo(32 * 5));
        objectPath.getElements().add(new HLineTo(32 * 2));
        objectPath.getElements().add(new VLineTo(32 * 8));
        objectPath.getElements().add(new HLineTo(32 * 7));
        objectPath.getElements().add(new VLineTo(32 * 1));
        objectPath.getElements().add(new HLineTo(32 * 10));
        objectPath.getElements().add(new VLineTo(32 * 11));
        objectPath.getElements().add(new HLineTo(32 * 15));
        objectPath.getElements().add(new VLineTo(32 * 5));
        objectPath.getElements().add(new HLineTo(32 * 27));
        objectPath.getElements().add(new VLineTo(32 * 14));
        objectPath.getElements().add(new HLineTo(32 * 6));
        objectPath.getElements().add(new VLineTo(32 * 11));
        objectPath.getElements().add(new HLineTo(32 * 2));
        objectPath.getElements().add(new VLineTo(32 * 20));
        objectPath.getElements().add(new HLineTo(32 * 6));
        objectPath.getElements().add(new VLineTo(32 * 18));
        objectPath.getElements().add(new HLineTo(32 * 21));
        objectPath.getElements().add(new VLineTo(32 * 19));
        objectPath.getElements().add(new HLineTo(32 * 30));
        healthBarPath = new Path();
        healthBarPath.getElements().add(new MoveTo(32 * -2, 32 * 2-16));
        healthBarPath.getElements().add(new HLineTo(32 * 4));
        healthBarPath.getElements().add(new VLineTo(32 * 5-16));
        healthBarPath.getElements().add(new HLineTo(32 * 2));
        healthBarPath.getElements().add(new VLineTo(32 * 8-16));
        healthBarPath.getElements().add(new HLineTo(32 * 7));
        healthBarPath.getElements().add(new VLineTo(32 * 1-16));
        healthBarPath.getElements().add(new HLineTo(32 * 10));
        healthBarPath.getElements().add(new VLineTo(32 * 11-16));
        healthBarPath.getElements().add(new HLineTo(32 * 15));
        healthBarPath.getElements().add(new VLineTo(32 * 5-16));
        healthBarPath.getElements().add(new HLineTo(32 * 27));
        healthBarPath.getElements().add(new VLineTo(32 * 14-16));
        healthBarPath.getElements().add(new HLineTo(32 * 6));
        healthBarPath.getElements().add(new VLineTo(32 * 11-16));
        healthBarPath.getElements().add(new HLineTo(32 * 2));
        healthBarPath.getElements().add(new VLineTo(32 * 20-16));
        healthBarPath.getElements().add(new HLineTo(32 * 6));
        healthBarPath.getElements().add(new VLineTo(32 * 18-16));
        healthBarPath.getElements().add(new HLineTo(32 * 21));
        healthBarPath.getElements().add(new VLineTo(32 * 19-16));
        healthBarPath.getElements().add(new HLineTo(32 * 30));
        objectAnimation = new PathTransition();
        objectAnimation.setDuration(Duration.seconds(speed));
        objectAnimation.setNode(iv);
        objectAnimation.setPath(objectPath);
        objectAnimation.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        objectAnimation.setCycleCount(0);
        objectAnimation.setAutoReverse(false);
        objectAnimation.setOnFinished(actionEvent -> {
            player.setLives(player.getLives()-1);
            onDestroyed();
        });
        hitBoxAnimation = new PathTransition();
        hitBoxAnimation.setDuration(Duration.seconds(speed));
        hitBoxAnimation.setNode(hitBox);
        hitBoxAnimation.setPath(objectPath);
        hitBoxAnimation.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        hitBoxAnimation.setCycleCount(0);
        hitBoxAnimation.setAutoReverse(false);
        hitBoxAnimation.setOnFinished(actionEvent -> {
            onDestroyed();
        });
        healthBarAnimation=new PathTransition();
        healthBarAnimation.setDuration(Duration.seconds(speed));
        healthBarAnimation.setNode(healthBar);
        healthBarAnimation.setPath(healthBarPath);
        healthBarAnimation.setCycleCount(0);
        healthBarAnimation.setAutoReverse(false);
        healthBarAnimation.play();
        hitBoxAnimation.play();
        objectAnimation.play();
    }

    public void onDestroyed() {
        root.getChildren().remove(iv);
        root.getChildren().remove(hitBox);
        root.getChildren().remove(healthBar);
        player.setCash(player.getCash()+cost);
        existence = false;
        for (int i = 0; i < movableObjects.size(); i++) {
//            if(movableObjects.get(i).getClass()==Tank.class) {
            if (movableObjects.get(i).isExistence() == false) {
                System.out.println("from tank class: "+movableObjects.size());
                movableObjects.remove(i);
                System.out.println("from tank class removed: "+movableObjects.size());
            }
//            }
        }
    }

    public Rectangle getHitBox() {
        return hitBox;
    }
    public boolean isExistence() {
        return existence;
    }
}
