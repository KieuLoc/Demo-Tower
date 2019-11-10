package sample;
import javafx.animation.RotateTransition;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.Main;

public class Boss extends  MovableObject{
    public int blood;
    public int dameByTower;
    public boolean survive = true;
    public void hurt( int dameByTower){
        this.blood = this.blood - dameByTower;
    }
    public Boss(Group root) {
        super(root);
    }
    public void Survive(){
        if(this.blood <=0){
            survive = false;
        }
    }
    Group root;
    Image bodyImg=new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/towerDefense_tile268.png");
    ImageView iv= new ImageView(bodyImg);
}

