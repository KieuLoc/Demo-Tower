package sample;

import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.util.Duration;


public class MovableObject {
    int i = 0;
    int j = 1;
    int x = i * 32 + 16;
    int y = j * 32 + 16;
    int speed = 1;
    Group root;
    Image bodyImg=new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/towerDefense_tile268.png");
    ImageView iv= new ImageView(bodyImg);


    Image ImgF =new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/towerDefense_tile270.png");
    ImageView ivv= new ImageView(ImgF);

    public MovableObject(Group root){
        this.root=root;
    }

//    public ImageView loadImageView(){
//        iv.setFitHeight(32);
//        iv.setFitWidth(32);
//        return iv;
//    }

    //    public void render(GraphicsContext gc){
//        SnapshotParameters params = new SnapshotParameters();
//        params.setFill(Color.TRANSPARENT);
//        Image image=iv.snapshot(params,null);
//        gc.drawImage(image,x,y,32,32);
//    }
    public void move(){
        iv.setFitHeight(32);
        iv.setFitWidth(32);
        Path objectPath = new Path();
        objectPath.getElements().add(new MoveTo(32*0,32*2));
        objectPath.getElements().add(new HLineTo(32*4));
        objectPath.getElements().add(new VLineTo(32*5));
        objectPath.getElements().add(new HLineTo(32*2));
        objectPath.getElements().add(new VLineTo(32*8));
        objectPath.getElements().add(new HLineTo(32*7));
        objectPath.getElements().add(new VLineTo(32*1));
        objectPath.getElements().add(new HLineTo(32*10));
        objectPath.getElements().add(new VLineTo(32*11));
        objectPath.getElements().add(new HLineTo(32*15));
        objectPath.getElements().add(new VLineTo(32*5));
        objectPath.getElements().add(new HLineTo(32*27));
        objectPath.getElements().add(new VLineTo(32*14));
        objectPath.getElements().add(new HLineTo(32*6));
        objectPath.getElements().add(new VLineTo(32*11));
        objectPath.getElements().add(new HLineTo(32*2));
        objectPath.getElements().add(new VLineTo(32*20));
        objectPath.getElements().add(new HLineTo(32*6));
        objectPath.getElements().add(new VLineTo(32*18));
        objectPath.getElements().add(new HLineTo(32*21));
        objectPath.getElements().add(new VLineTo(32*19));
        objectPath.getElements().add(new HLineTo(32*30));

        PathTransition objectAnimation= new PathTransition();
        objectAnimation.setDuration(Duration.seconds(15));
        objectAnimation.setNode(iv);
        objectAnimation.setPath(objectPath);
        objectAnimation.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        objectAnimation.setCycleCount(0);
        objectAnimation.setAutoReverse(false);

        objectAnimation.play();
        root.getChildren().add(iv);
    }
    public void moveFly(){
        ivv.setFitHeight(50);
        ivv.setFitWidth(50);
        Path objectPath = new Path();
        objectPath.getElements().add(new MoveTo(50*0,50*2));
        objectPath.getElements().add(new HLineTo(50*4));
        objectPath.getElements().add(new VLineTo(50*5));
        objectPath.getElements().add(new HLineTo(50*2));
        objectPath.getElements().add(new VLineTo(50*8));
        objectPath.getElements().add(new HLineTo(50*7));
        objectPath.getElements().add(new VLineTo(50*11));
        objectPath.getElements().add(new HLineTo(50*15));
        objectPath.getElements().add(new VLineTo(50*5));
        objectPath.getElements().add(new HLineTo(50*27));

        PathTransition objectAnimation= new PathTransition();
        objectAnimation.setDuration(Duration.seconds(15));
        objectAnimation.setNode(ivv);
        objectAnimation.setPath(objectPath);
        objectAnimation.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        objectAnimation.setCycleCount(0);
        objectAnimation.setAutoReverse(false);

        objectAnimation.play();
        root.getChildren().add(ivv);
    }
}
