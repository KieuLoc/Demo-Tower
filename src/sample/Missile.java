package sample;

import javafx.animation.PathTransition;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Missile extends MissileTower {
    private Image missileImg = new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/towerDefense_tile252.png");
    private ImageView missileIv;
    private boolean missileExistence;
    private double speed;
    private double currentX;
    private double currentY;
    private Point2D target;
    private Rectangle hitBox;
    double distance;
    long frameTime=System.currentTimeMillis();

    public Missile(double x, double y, double range, Group root) {
        this.x = x;
        this.y = y;
        this.root = root;
        this.range = range;
        this.currentX = x;
        this.currentY = y;
        missileIv = new ImageView();
        missileIv.setImage(missileImg);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        missileIv.setRotate(90);
        Image image= missileIv.snapshot(params,null);
        missileIv.setImage(image);
        missileIv.setTranslateX(-10000);
        missileIv.setTranslateY(-10000);
        missileIv.setFitHeight(32);
        missileIv.setFitWidth(32);
        root.getChildren().add(missileIv);
        hitBox=new Rectangle();
        hitBox.setWidth(20);
        hitBox.setHeight(8);
        hitBox.setFill(Color.TRANSPARENT);
        hitBox.setTranslateX(-1000);
        hitBox.setTranslateY(-1000);
        root.getChildren().add(hitBox);
    }

    public void fire( double distanceFromTower, int towerIndex, int enemyIndex) {
        if(distanceFromTower<=range && loaded==true && currentX!=-1000 && currentY != -1000) {
            try {
                enemyX = movableObjects.get(enemyIndex).getX() + 16;
                enemyY = movableObjects.get(enemyIndex).getY() + 16;
            }catch (Exception e){}
            target = new Point2D(enemyX, enemyY);
            Point2D currentLocation = new Point2D(currentX, currentY);
            distance = currentLocation.distance(target)/2;
            Path path = new Path();
            path.getElements().add(new MoveTo(currentX, currentY));
            currentX = missileIv.getTranslateX() + 16;
            currentY = missileIv.getTranslateY() + 16;
            path.getElements().add(new LineTo(enemyX, enemyY));
            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.millis(distance));
            pathTransition.setNode(missileIv);
            pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition.setPath(path);
            pathTransition.setCycleCount(0);
            pathTransition.setAutoReverse(false);
            PathTransition hitBoxTransition = new PathTransition();
            hitBoxTransition.setDuration(Duration.millis(distance));
            hitBoxTransition.setNode(hitBox);
            hitBoxTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            hitBoxTransition.setPath(path);
            hitBoxTransition.setCycleCount(0);
            hitBoxTransition.setAutoReverse(false);
            hitBoxTransition.play();
            pathTransition.play();
            missileIv.setVisible(true);
            hitBox.setVisible(true);
//            System.out.println(System.currentTimeMillis()-frameTime);
            frameTime=System.currentTimeMillis();
            try {
                if(hitBox.getBoundsInParent().intersects(movableObjects.get(enemyIndex).getHitBox().getBoundsInParent())){
                    try {
                        movableObjects.get(enemyIndex).setHealth(movableObjects.get(enemyIndex).getHealth() - towers.get(towerIndex).getDamageDealt());
                        onDestroyed( enemyIndex, movableObjects.get(enemyIndex).getHealth());
                    } catch (Exception e) {
                        onDestroyed( enemyIndex, movableObjects.get(enemyIndex).getHealth());
                    }
                }
                else {
                    loaded=true;
                }
            }catch (Exception e){
                loaded=true;
            }
        }
    }

    public boolean isMissileExistence() {
        return missileExistence;
    }

    public void setMissileExistence(boolean missileExistence) {
        this.missileExistence = missileExistence;
    }

    public void onDestroyed(int enemyIndex, int health) {
        root.getChildren().remove(missileIv);
        root.getChildren().remove(hitBox);
        hitBox=null;
        loaded=false;
        if(health<=0) {
            movableObjects.get(enemyIndex).onDestroyed();
        }
    }

    @Override
    public boolean isLoaded() {
        return super.isLoaded();
    }

    @Override
    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}
