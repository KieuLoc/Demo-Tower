package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Main extends Application {
    MediaPlayer mediaPlayer;
    Group root=new Group();
    Scene mainScene= new Scene(root, 1280 , 704);
    GraphicsContext gc;
    Canvas canvas;
    Player player=new Player(root);
    ObjectManager level= new ObjectManager(root,player);
    int number=5;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Media musicFile = new Media("file:///C:/Users/admin/Downloads/background_ms.mp3");
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(2);

        canvas=new Canvas(32*30,32*22);
        gc=canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        root.getChildren().addAll(player.lives(),player.cash());
        primaryStage.setTitle("Tower Defense");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        drawMap(gc);
        menu();
        AimAndFire();
    }
    //    public static final String[][] MAP_SPRITES =new String[][]{
//            { "024", "024", "024", "024", "024", "024", "003", "047", "047", "047","004", "024", "024", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024" },
//            { "047", "047", "047", "047", "004", "024", "025", "299", "001", "002","023", "024", "024", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024" },
//            { "001", "001", "001", "002", "023", "024", "025", "023", "024", "025","023", "024", "024", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024" },
//            { "024", "024", "024", "025", "023", "024", "025", "023", "024", "025","023", "024", "024", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024" },
//            { "024", "003", "047", "048", "023", "024", "025", "023", "024", "025","023", "024", "024", "024", "003", "047", "047", "047", "047", "047","047", "047", "047", "047", "047", "047", "047", "004", "024", "024" },
//            { "024", "025", "299", "001", "027", "024", "025", "023", "024", "025","023", "024", "024", "024", "025", "299", "001", "001", "001", "001","001", "001", "001", "001", "001", "001", "002", "023", "024", "024" },
//            { "024", "025", "023", "024", "024", "024", "025", "023", "024", "025","023", "024", "024", "024", "025", "023", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "025", "023", "024", "024" },
//            { "024", "025", "046", "047", "047", "047", "048", "023", "024", "025","023", "024", "024", "024", "025", "023", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "025", "023", "024", "024" },
//            { "024", "026", "001", "001", "001", "001", "001", "027", "024", "025","023", "024", "024", "024", "025", "023", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "025", "023", "024", "024" },
//            { "024", "024", "024", "024", "024", "024", "024", "024", "024", "025","023", "024", "024", "024", "025", "023", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "025", "023", "024", "024" },
//            { "024", "003", "047", "047", "047", "047", "004", "024", "024", "025","046", "047", "047", "047", "048", "023", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "025", "023", "024", "024" },
//            { "024", "025", "299", "001", "001", "002", "023", "024", "024", "026","001", "001", "001", "001", "001", "027", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "025", "023", "024", "024" },
//            { "024", "025", "023", "024", "024", "025", "023", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "025", "023", "024", "024" },
//            { "024", "025", "023", "024", "024", "025", "046", "047", "047", "047","047", "047", "047", "047", "047", "047", "047", "047", "047", "047","047", "047", "047", "047", "047", "047", "048", "023", "024", "024" },
//            { "024", "025", "023", "024", "024", "026", "001", "001", "001", "001","001", "001", "001", "001", "001", "001", "001", "001", "001", "001","001", "001", "001", "001", "001", "001", "001", "027", "024", "024" },
//            { "024", "025", "023", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024" },
//            { "024", "025", "023", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024" },
//            { "024", "025", "023", "024", "024", "003", "047", "047", "047", "047","047", "047", "047", "047", "047", "047", "047", "047", "047", "047","047", "004", "024", "024", "024", "024", "024", "024", "024", "024" },
//            { "024", "025", "023", "024", "024", "025", "299", "001", "001", "001","001", "001", "001", "001", "001", "001", "001", "001", "001", "001","002", "046", "047", "047", "047", "047", "047", "047", "047", "047" },
//            { "024", "025", "046", "047", "047", "048", "023", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024","026", "001", "001", "001", "001", "001", "001", "001", "001", "001" },
//            { "024", "026", "001", "001", "001", "001", "027", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024" },
//            { "024", "024", "024", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024","024", "024", "024", "024", "024", "024", "024", "024", "024", "024" },
//    };
    public void drawMap(GraphicsContext gc){
        gc.drawImage(new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/remap.png"),0,0,960,704);
    }

    public void menu(){
        towerButtons();
        towerRemovalButton();
        musicButton();
    }
    public void musicButton(){
        Button play, pause, stop;
        play = new Button("Start");
        pause = new Button("Pause");
        stop = new Button("Stop");
        play.resize(64,32);
        pause.resize(64,32);
        stop.resize(64,32);
        play.relocate(32*31+16,32*19+16);
        stop.relocate(32*31+16+40,32*19+16);
        pause.relocate(32*31+16+80,32*19+16);
        root.getChildren().addAll(play, stop, pause);
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.play();
            }
        });
        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.pause();
            }
        });
        stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.stop();
            }
        });
    }

    public void towerButtons(){
        Button sniperTowerButton =new Button();
        ImageView sniperTowerButtonIv =new ImageView(new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/towerDefense_tile249.png"));
        sniperTowerButtonIv.setFitWidth(64);
        sniperTowerButtonIv.setFitHeight(64);
        sniperTowerButton.setGraphic(sniperTowerButtonIv);
        sniperTowerButton.relocate(32*31+16,32*0+16);
        root.getChildren().add(sniperTowerButton);
        sniperTowerButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        int i=(int) mouseEvent.getSceneX()/32;
                        int j=(int) mouseEvent.getSceneY()/32;
                        System.out.println("clicked "+i+" "+j);
                        level.renderSniperTower(i,j);
                    }
                });
            }
        });
        Button missileTowerButton =new Button();
        ImageView missileTowerButtonIv =new ImageView(new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/towerDefense_tile206.png"));
        missileTowerButtonIv.setFitWidth(64);
        missileTowerButtonIv.setFitHeight(64);
        missileTowerButton.setGraphic(missileTowerButtonIv);
        missileTowerButton.relocate(32*35+16,32*0+16);
        root.getChildren().add(missileTowerButton);
        missileTowerButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        int i=(int) mouseEvent.getSceneX()/32;
                        int j=(int) mouseEvent.getSceneY()/32;
                        System.out.println("clicked "+i+" "+j);
                        level.renderMissileTower(i,j);
                    }
                });
            }
        });
        Button machineGunTowerButton=new Button();
        ImageView machineGunTowerButtonIv=new ImageView("file:src/TowerDefense/AssetsKit_2/PNG/Retina/towerDefense_tile250.png");
        machineGunTowerButtonIv.setFitWidth(64);
        machineGunTowerButtonIv.setFitHeight(64);
        machineGunTowerButton.setGraphic(machineGunTowerButtonIv);
        machineGunTowerButton.relocate(32*35+16,32*3);
        root.getChildren().add(machineGunTowerButton);
        machineGunTowerButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        int i=(int) mouseEvent.getSceneX()/32;
                        int j=(int) mouseEvent.getSceneY()/32;
                        System.out.println("clicked "+i+" "+j);
                        level.renderMachineGunTower(i,j);
                    }
                });
            }
        });
        Button nextLevel=new Button("Next Level");
        nextLevel.resize(64,32);
        nextLevel.relocate(32*32+16,32*12);
        root.getChildren().addAll(nextLevel);
        nextLevel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    if(number<20){
                        level.renderEnemy(number);
                        number+=5;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void towerRemovalButton(){
        Button removeButton=new Button("SELL");
        removeButton.resize(64,32);
        removeButton.relocate(32*32+16,32*20+16);
        root.getChildren().add(removeButton);
        removeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                level.towerRemove();
            }
        });
    }

    public void AimAndFire(){
        level.towerReadyAim();
    }

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

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
