package sample;

import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.util.ArrayList;

public class MachineGunTower extends Tower {
    public double x,y;
    public boolean loaded;
    public double degree;
    public double distance;
    ImageView baseIv;
    ImageView gunIv;
    ImageView firingGunIv;
    double enemyX;
    double enemyY;
    int towerIndex;
    int enemyIndex;
    int cost=100;
    Image base= new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/towerDefense_tile181.png");
    Image gun= new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/towerDefense_tile250.png");
    Image firingGun= new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/towerDefense_tile301.png");


    public MachineGunTower(){
    }

    public MachineGunTower(int i, int j, Group root, Player player){
        super(i,j,root,player);
        existence=true;
        range=200;
    }

    public void render(){
        if(player.getCash()>=cost){
            player.setCash(player.getCash()-cost);
        }
        int x=i*32;
        int y=j*32;
        baseIv = new ImageView(base);
        baseIv.setFitWidth(32);
        baseIv.setFitHeight(32);
        baseIv.setLayoutX(x);
        baseIv.setLayoutY(y);
        root.getChildren().add(baseIv);
        gunIv = new ImageView(gun);
        gunIv.setFitWidth(32);
        gunIv.setFitHeight(32);
        gunIv.setLayoutX(x);
        gunIv.setLayoutY(y);
        root.getChildren().add(gunIv);
        firingGunIv = new ImageView(firingGun);
        firingGunIv.setFitWidth(64);
        firingGunIv.setFitHeight(64);
        firingGunIv.setLayoutX(x-16);
        firingGunIv.setLayoutY(y-16);
        root.getChildren().add(firingGunIv);
        gunIv.setVisible(true);
        firingGunIv.setVisible(false);
    }

    public void remove(){
        gunIv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player.setCash(player.getCash()+50);
                System.out.println("Clicked GunIV");
                root.getChildren().remove(baseIv);
                root.getChildren().remove(gunIv);
                existence=false;
                for(int i=0;i<towers.size();i++){
                    if(towers.get(i).existence==false){
                        towers.remove(i);
                    }
                }
            }
        });
        baseIv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player.setCash(player.getCash()+50);
                root.getChildren().remove(baseIv);
                root.getChildren().remove(gunIv);
                root.getChildren().remove(firingGunIv);
                existence=false;
                for(int i=0;i<towers.size();i++){
                    if(towers.get(i).existence==false){
                        towers.remove(i);
                        System.out.println(towers.size());
                    }
                }
            }
        });
        firingGunIv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player.setCash(player.getCash()+50);
                root.getChildren().remove(baseIv);
                root.getChildren().remove(gunIv);
                root.getChildren().remove(firingGunIv);
                existence=false;
                for(int i=0;i<towers.size();i++){
                    if(towers.get(i).existence==false){
                        towers.remove(i);
                        System.out.println(towers.size());
                    }
                }
            }
        });
    }

    public void ReadyAim(double enemyX, double enemyY, int towerIndex, int enemyIndex){
        if(enemyIndex!=-1) {
            gunIv.setVisible(true);
            firingGunIv.setVisible(false);
            this.towerIndex = towerIndex;
            this.enemyIndex = enemyIndex;
            this.enemyX = enemyX + 16;
            this.enemyY = enemyY + 16;
            x = i * 32 + 16;
            y = j * 32 + 16;
            RotateTransition aim;
            RotateTransition temp;
            Point2D target = new Point2D(enemyX + 16, enemyY + 16);
            Point2D tower = new Point2D(x, y);
            Point2D reference = new Point2D(x, 0);
            distance = target.distance(tower);
            degree = tower.angle(target, reference);
            if (enemyX + 16 < x) {
                degree = 360 - degree;
            }
            aim = new RotateTransition(Duration.millis(50), gunIv);
            temp = new RotateTransition(Duration.millis(50), firingGunIv);
            aim.setToAngle(degree);
            temp.setToAngle(degree);
            aim.setOnFinished(actionEvent -> {
                gunIv.setVisible(false);
                firingGunIv.setVisible(true);
                movableObjects.get(enemyIndex).setHealth(movableObjects.get(enemyIndex).getHealth() - towers.get(towerIndex).getDamageDealt());
                if(movableObjects.get(enemyIndex).getHealth()<=0){
                    movableObjects.get(enemyIndex).onDestroyed();
                }
                System.out.println(movableObjects.get(enemyIndex).getHealth());
            });
            aim.play();
            temp.play();
        }
        else {
            gunIv.setVisible(true);
            firingGunIv.setVisible(false);
        }
    }

    public void shoot(){
    }

    @Override
    public int getDamageDealt() {
        damageDealt=10;
        return super.getDamageDealt();
    }

    @Override
    public double getRange() {
        return super.getRange();
    }
}
