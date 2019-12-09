package sample;

import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class MissileTower extends Tower {
    protected double x,y;
    protected boolean loaded;
    protected double degree;
    protected double distanceFromTower;
    ImageView baseIv;
    ImageView gunIv;
    double enemyX;
    double enemyY;
    int towerIndex;
    int enemyIndex;
    protected Missile missile;
    Image base= new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/towerDefense_tile180.png");
    Image gun= new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/towerDefense_tile206.png");
    Image emptyGun=new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/towerDefense_tile229.png");


    public MissileTower(){
    }

    public MissileTower(int i, int j, Group root,Player player){
        super(i,j,root,player);
        existence=true;
        range=600;
    }

    public void render(){
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
        loaded=false;
        missile = new Missile(x, y, range, root);
    }

    public void remove(){
        gunIv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                root.getChildren().remove(baseIv);
                root.getChildren().remove(gunIv);
                existence=false;
                for(int i=0;i<towers.size();i++){
                    if(towers.get(i).existence==false){
                        towers.remove(i);
                        System.out.println(towers.size());
                    }
                }
            }
        });
        baseIv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                root.getChildren().remove(baseIv);
                root.getChildren().remove(gunIv);
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
        Point2D target = new Point2D(enemyX + 16, enemyY + 16);
        Point2D tower = new Point2D(x, y);
        Point2D reference = new Point2D(x, 0);
        distanceFromTower = target.distance(tower);
        loaded=missile.isLoaded();
        if(!loaded) {
            missile = null;
            this.towerIndex = towerIndex;
            this.enemyIndex = enemyIndex;
            this.enemyX = enemyX + 16;
            this.enemyY = enemyY + 16;
            x = i * 32 + 16;
            y = j * 32 + 16;
            RotateTransition aim;
//            if (distanceFromTower <= range ) {
            degree = tower.angle(target, reference);
            if (enemyX + 16 < x) {
                degree = 360 - degree;
            }
            aim = new RotateTransition(Duration.millis(50), gunIv);
            aim.setToAngle(degree);
            aim.setOnFinished(actionEvent -> {
                missile = new Missile(x, y, range, root);
                missile.setLoaded(true);
            });
//            System.out.println(missile.isLoaded());
            aim.play();
//            }
        }
    }

    public void shoot(){
        x=i*32+16;
        y=j*32+16;
        Point2D target=new Point2D(enemyX,enemyY);
        Point2D tower=new Point2D(x,y);
        distanceFromTower =target.distance(tower);
        try {
            missile.fire(distanceFromTower, towerIndex, enemyIndex);
        }catch (Exception e){}
    }

    @Override
    public int getDamageDealt() {
        damageDealt=20;
        return super.getDamageDealt();
    }

    @Override
    public double getRange() {
        return super.getRange();
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

}
