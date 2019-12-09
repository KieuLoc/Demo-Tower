package sample;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Bullet extends SniperTower {
    MediaPlayer mediaPlayer;
    Image bullet= new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/towerDefense_tile272.png");
    ImageView bulletIV;
    boolean bulletExistence =true;

    public Bullet(boolean loaded,double x,double y,double distance,double range,Group root,Player player){
        this.player=player;
        this.loaded=loaded;
        this.x=x;
        this.y=y;
        this.root=root;
        this.distance=distance;
        this.range=range;
    }

    public void fire(double enemyX, double enemyY, int towerIndex , int enemyIndex, ImageView gunIv, ImageView firingGunIv){
        bulletIV = new ImageView();
        bulletIV.setImage(bullet);
        root.getChildren().add(bulletIV);
        bulletIV.setTranslateX(-1000);
        bulletIV.setTranslateY(-1000);
        bulletIV.setVisible(false);
        if (distance <= range && loaded) {
            Path projectilePath = new Path();
            LineTo lineTo = new LineTo(enemyX, enemyY);
            bulletIV.setFitHeight(32);
            bulletIV.setFitWidth(32);
            projectilePath.getElements().add(new MoveTo(x, y));
            projectilePath.getElements().add(lineTo);
            PathTransition projectile = new PathTransition();
            projectile.setDuration(Duration.millis(80));
            projectile.setNode(bulletIV);
            projectile.setPath(projectilePath);
            projectile.setCycleCount(0);
            projectile.setAutoReverse(false);
            projectile.setOnFinished(action -> {
                try {
                    movableObjects.get(enemyIndex).setHealth(movableObjects.get(enemyIndex).getHealth() - towers.get(towerIndex).getDamageDealt());
                    onDestroyed(enemyIndex, movableObjects.get(enemyIndex).getHealth());
                    System.out.println(movableObjects.get(enemyIndex).getHealth());
                    Media musicFile = new Media("file:///C:/Users/admin/Downloads/bullet.mp3");
                    mediaPlayer = new MediaPlayer(musicFile);
                    mediaPlayer.setAutoPlay(true);
                    mediaPlayer.setVolume(2);
                } catch (Exception e) {
                    root.getChildren().remove(bulletIV);
                    bulletExistence = false;
                    for (int i = 0; i < bullets.size(); i++) {
                        if (bullets.get(i).bulletExistence == false) {
                            bullets.remove(i);
                        }
                    }
                    firingGunIv.setVisible(false);
                    gunIv.setVisible(true);
                }
            });
            projectile.play();
            loaded = false;
            Timeline timeline = new Timeline();
            KeyFrame keyFrame = new KeyFrame(Duration.millis(20), actionEvent -> {
                gunIv.setVisible(false);
                firingGunIv.setVisible(true);
                bulletIV.setVisible(true);
            });
            timeline.getKeyFrames().add(keyFrame);
            timeline.setCycleCount(0);
            timeline.play();
            firingGunIv.setVisible(false);
            gunIv.setVisible(true);
        }
        firingGunIv.setVisible(false);
        gunIv.setVisible(true);
    }

    public void onDestroyed(int enemyIndex, int health){
        root.getChildren().remove(bulletIV);
        bulletExistence =false;
        if(health<=0) {
            movableObjects.get(enemyIndex).onDestroyed();
        }
        for(int i=0;i<bullets.size();i++){
            if(bullets.get(i).bulletExistence ==false){
                bullets.remove(i);
            }
        }
        gunIv.setVisible(true);
        firingGunIv.setVisible(false);
    }
}
