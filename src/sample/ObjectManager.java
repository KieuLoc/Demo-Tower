package sample;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class ObjectManager {
    protected static ArrayList<MovableObject> movableObjects = new ArrayList<>();
    protected static ArrayList<Tower> towers= new ArrayList<>();
    protected Group root;
    protected Player player;
    int i;
    int shortestIndex;
    public static final String[][] MapLayout =new String[][]{
            { "024", "024", "024", "024", "024", "024", "003", "047", "047", "047","004", "024", "024", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024" },
            { "047", "047", "047", "047", "004", "024", "025", "299", "001", "002","023", "024", "024", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024" },
            { "001", "001", "001", "002", "023", "024", "025", "023", "024", "025","023", "024", "024", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024" },
            { "024", "024", "024", "025", "023", "024", "025", "023", "024", "025","023", "024", "024", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024" },
            { "024", "003", "047", "048", "023", "024", "025", "023", "024", "025","023", "024", "024", "024", "003", "047", "047", "047", "047", "047","047", "047", "047", "047", "047", "047", "047", "004", "024", "024" },
            { "024", "025", "299", "001", "027", "024", "025", "023", "024", "025","023", "024", "024", "024", "025", "299", "001", "001", "001", "001","001", "001", "001", "001", "001", "001", "002", "023", "024", "024" },
            { "024", "025", "023", "024", "024", "024", "025", "023", "024", "025","023", "024", "024", "024", "025", "023", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "025", "023", "024", "024" },
            { "024", "025", "046", "047", "047", "047", "048", "023", "024", "025","023", "024", "024", "024", "025", "023", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "025", "023", "024", "024" },
            { "024", "026", "001", "001", "001", "001", "001", "027", "024", "025","023", "024", "024", "024", "025", "023", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "025", "023", "024", "024" },
            { "024", "024", "024", "024", "024", "024", "024", "024", "024", "025","023", "024", "024", "024", "025", "023", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "025", "023", "024", "024" },
            { "024", "003", "047", "047", "047", "047", "004", "024", "024", "025","046", "047", "047", "047", "048", "023", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "025", "023", "024", "024" },
            { "024", "025", "299", "001", "001", "002", "023", "024", "024", "026","001", "001", "001", "001", "001", "027", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "025", "023", "024", "024" },
            { "024", "025", "023", "024", "024", "025", "023", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "025", "023", "024", "024" },
            { "024", "025", "023", "024", "024", "025", "046", "047", "047", "047","047", "047", "047", "047", "047", "047", "047", "047", "047", "047","047", "047", "047", "047", "047", "047", "048", "023", "024", "024" },
            { "024", "025", "023", "024", "024", "026", "001", "001", "001", "001","001", "001", "001", "001", "001", "001", "001", "001", "001", "001","001", "001", "001", "001", "001", "001", "001", "027", "024", "024" },
            { "024", "025", "023", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024" },
            { "024", "025", "023", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024" },
            { "024", "025", "023", "024", "024", "003", "047", "047", "047", "047","047", "047", "047", "047", "047", "047", "047", "047", "047", "047","047", "004", "024", "024", "024", "024", "024", "024", "024", "024" },
            { "024", "025", "023", "024", "024", "025", "299", "001", "001", "001","001", "001", "001", "001", "001", "001", "001", "001", "001", "001","002", "046", "047", "047", "047", "047", "047", "047", "047", "047" },
            { "024", "025", "046", "047", "047", "048", "023", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024","026", "001", "001", "001", "001", "001", "001", "001", "001", "001" },
            { "024", "026", "001", "001", "001", "001", "027", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024" },
            { "024", "024", "024", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024" },
    };

    public ObjectManager(Group root,Player player){
        this.player=player;
        this.root=root;
    }

    public ObjectManager() {

    }

    public void createMovableObject(int number){
        Rectangle cheat=new Rectangle();
        cheat.setWidth(32);
        cheat.setHeight(32);
        cheat.setFill(Color.RED);
        cheat.setTranslateX(32*39);
        cheat.setTranslateY(32*21);
        root.getChildren().add(cheat);
        cheat.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                for(int i=0;i<movableObjects.size();i++){
                    movableObjects.get(i).onDestroyed();
                }
            }
        });
        for (int i = 0; i < number; i++) {
            int x = (int) ((Math.random() * ((3 - 1) + 1)) + 1);
            if (x == 1) {
                MovableObject object = new Trooper(root, player);
                movableObjects.add(object);
            } else if (x == 2) {
                MovableObject object1 = new Trooper1(root, player);
                movableObjects.add(object1);
            } else if (x == 3) {
                MovableObject object2 = new Tank(root, player);
                movableObjects.add(object2);
            }
        }
    }

    public void renderEnemy(int number) throws InterruptedException {
        if(movableObjects.isEmpty()) {
            long spawnTime = 1;
            createMovableObject(number);
            i = 0;
            Timeline timeline = new Timeline();
            KeyFrame keyFrame;
            keyFrame = new KeyFrame(
                    Duration.seconds(spawnTime),
                    actionEvent -> {
                        try {
                            movableObjects.get(i).move();
                        } catch (Exception e) {
                        }
                        i++;
                    }
            );
            timeline.getKeyFrames().add(keyFrame);
            timeline.setCycleCount(movableObjects.size());
            timeline.play();
        }
    }

    public void renderSniperTower(int i, int j){
        Tower tower=new SniperTower(i,j,root,player);
        if(MapLayout[j][i]=="024"){
            tower.render();
            MapLayout[j][i]="000";
            towers.add(tower);
        }
        else{
            System.out.println("On track");
        }
    }

    public void renderMissileTower(int i, int j){
        Tower tower=new MissileTower(i,j,root,player);
        if(MapLayout[j][i]=="024"){
            tower.render();
            MapLayout[j][i]="000";
            towers.add(tower);
        }
        else{
            System.out.println("On track");
        }
    }

    public void renderMachineGunTower(int i, int j){
        Tower tower=new MachineGunTower(i,j,root,player);
        if(MapLayout[j][i]=="024"){
            tower.render();
            MapLayout[j][i]="000";
            towers.add(tower);
        }
        else{
            System.out.println("On track");
        }
    }

    public void towerRemove(){
        for(int i=0;i<towers.size();i++){
            MapLayout[towers.get(i).j][towers.get(i).i]="024";
            towers.get(i).remove();
        }
    }
    public void towerReadyAim(){
        Timeline readyAim = new Timeline();
        KeyFrame Aim = new KeyFrame(Duration.millis(70), actionEvent -> {
//            System.out.println(movableObjects.size());
            if(movableObjects.size()>0) {
                for (int j = 0; j < towers.size(); j++) {
                    shortestIndex = -1;
                    double shortestDistant = Double.MAX_VALUE;
                    Point2D tower = new Point2D(towers.get(j).i * 32 + 16, towers.get(j).j * 32 + 16);
                    for (int i = 0; i < movableObjects.size(); i++) {
                        Point2D enemy = new Point2D(movableObjects.get(i).getX(), movableObjects.get(i).getY());
                        double distance=enemy.distance(tower);
                        if (distance <= towers.get(j).getRange() && shortestDistant > distance) {
                            shortestDistant = distance;
                            shortestIndex = i;

                        }
                    }
                    try {
//                        System.out.println(j + " " + shortestIndex);
                        towers.get(j).ReadyAim(movableObjects.get(shortestIndex).getX(), movableObjects.get(shortestIndex).getY(),j,shortestIndex);
                    }catch (Exception e){
                        towers.get(j).ReadyAim(towers.get(j).i*32, 0,j,shortestIndex);
                    }
                }
            }
        });
        readyAim.getKeyFrames().addAll(Aim);
        readyAim.setCycleCount(Animation.INDEFINITE);
        readyAim.play();
        Timeline fire = new Timeline();
        KeyFrame shoot = new KeyFrame(Duration.millis(700), actionEvent -> {
            if(movableObjects.size()>0) {
                for (int j = 0; j < towers.size(); j++) {
                    if(towers.get(j).getClass()==SniperTower.class) {
                        towers.get(j).shoot();
                    }
                }
            }
        });
        fire.getKeyFrames().addAll(shoot);
        fire.setCycleCount(Animation.INDEFINITE);
        fire.play();
        AnimationTimer animationTimer= new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(movableObjects.size()>0) {
                    for (int j = 0; j < towers.size(); j++) {
                        if(towers.get(j).getClass()==MissileTower.class) {
                            towers.get(j).shoot();
                        }
                    }
                }
            }
        };
        animationTimer.start();
//        Timeline timeline=new Timeline();
//        KeyFrame keyFrame=new KeyFrame(Duration.millis(70), actionEvent -> {
//            if (movableObjects.size() > 0) {
//                for (int j = 0; j < towers.size(); j++) {
//                    if (towers.get(j).getClass() == MissileTower.class) {
//                        towers.get(j).shoot();
//                    }
//                }
//            }
//        });
//        timeline.getKeyFrames().add(keyFrame);
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();
    }

    public void collisionDetection() {

    }
}
