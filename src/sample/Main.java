package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    GraphicsContext gc;
    Tower towerTemp;
    List<Boss> bosses = new ArrayList<>();
    List<BossFly> bossFlyList = new ArrayList<>();
    Group root=new Group();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Canvas canvas=new Canvas(1280  ,704);
        gc=canvas.getGraphicsContext2D();
        Button button=new Button();
        Button button1 = new Button();
        Button button2 = new Button();
        Button button3 = new Button();
        Button buttonPlay = new Button();
        Image image =new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/bg.png");
        Image image1 = new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/xam.png");
        gc.drawImage(image,0,0,1280, 704);

        towerTemp = new Tower();

        root.getChildren().addAll(canvas,button, buttonPlay, button1, button2, button3);

        Button buttonNewGame = new Button();
        ImageView buttonImNewGame = new ImageView("file:src/TowerDefense/AssetsKit_2/PNG/Retina/newGame.png");
        buttonNewGame.setGraphic(buttonImNewGame);
       // buttonImNewGame.setFitHeight(30);
        //buttonImNewGame.setFitWidth(50);
        buttonNewGame.relocate(550,300);
        root.getChildren().add(buttonNewGame);

        buttonNewGame.setOnAction(new  EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gc.drawImage(image1,0,0,1280,704);
                //image button
                ImageView iv=new ImageView(new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/towerDefense_tile181.png"));
                iv.setFitWidth(32);
                iv.setFitHeight(32);
                button.setGraphic(iv);
                button.relocate(1000,32);
                ImageView iv1=new ImageView(new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/towerDefense_tile249.png"));
                iv1.setFitWidth(32);
                iv1.setFitHeight(32);
                button1.setGraphic(iv1);
                button1.relocate(1200,32);
                ImageView iv2=new ImageView(new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/towerDefense_tile250.png"));
                iv2.setFitWidth(32);
                iv2.setFitHeight(32);
                button2.setGraphic(iv2);
                button2.relocate(1000,120);
                ImageView iv3=new ImageView(new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/towerDefense_tile204.png"));
                iv3.setFitWidth(32);
                iv3.setFitHeight(32);
                button3.setGraphic(iv3);
                button3.relocate(1200,120);
                //NEWGAME
                buttonNewGame.setVisible(false);
                //buttonPlay
                ImageView btP = new ImageView(new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/play.png"));
                buttonPlay.setGraphic(btP);
                buttonPlay.relocate(1065,300);
                btP.setFitWidth(100);
                btP.setFitHeight(50);
                drawMap(gc);
                buttonPlay.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        render();
                    }
                });
            }
        });


        primaryStage.setTitle("Tower Defense");
        primaryStage.setScene(new Scene(root, 1280 , 704));
        primaryStage.show();
    }
        public static final String[][] MAP_SPRITES =new String[][]{
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
    public void drawMap(GraphicsContext gc){
        gc.drawImage(new Image("file:src/TowerDefense/AssetsKit_2/PNG/Retina/remap.png"),0,0,960,704);
    }
    public void render(){
        drawMap(gc);
        int number=10;
        int numberFly =5;
        long spawnTime=0;
        for ( int i=0;i<numberFly;i++){
            createBossFly();
        }
        for(int i=0;i<number;i++){
            createTankObject();
        }

        for(int i=0;i< bosses.size();){
            if(System.currentTimeMillis()-spawnTime>400){
                bosses.get(i).move();
                i++;
                System.out.println(System.currentTimeMillis()-spawnTime);
                spawnTime=System.currentTimeMillis();
            }
        }
        for(int i=0;i<bossFlyList.size();){
            if(System.currentTimeMillis()-spawnTime>900){
                bossFlyList.get(i).moveFly();
                i++;
                System.out.println(System.currentTimeMillis()-spawnTime);
                spawnTime=System.currentTimeMillis();
            }
        }
    }

    private void createTankObject() {
        Boss object = new Boss(root);
        bosses.add(object);
    }
    private  void  createBossFly(){
        BossFly bossFly = new BossFly(root);
        bossFlyList.add(bossFly);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
