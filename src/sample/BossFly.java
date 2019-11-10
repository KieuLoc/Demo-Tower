package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BossFly extends MovableObject {


    public int blood;
    public int dameByTower;
    public boolean survive = true;
    public void hurt( int dameByTower){
        this.blood = this.blood - dameByTower;
    }
    public BossFly(Group root) {
        super(root);
    }
    public void Survive(){
        if(this.blood <=0){
            survive = false;
        }
    }
    Group Froot;
    Image ImgF =new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/towerDefense_tile270.png");
    ImageView ivv= new ImageView(bodyImg);
}
