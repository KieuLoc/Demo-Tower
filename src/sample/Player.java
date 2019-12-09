package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Player {
    public int lives=30;
    public int cash=3000;
    public Group root;

    public Player(Group root){
        this.root=root;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public Text lives (){
        Text text=new Text();
        text.setX(32*32+16);
        text.setY(32*16);
        text.setFont(Font.font("Verdana"));
        text.setFont(new Font(20));
        text.setFill(Color.RED);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                text.setText("Lives: "+ lives);
            }
        };animationTimer.start();
        return text;
    }

    public Text cash (){
        Text text=new Text();
        text.setX(32*32+16);
        text.setY(32*17);
        text.setFont(Font.font("Verdana"));
        text.setFont(new Font(20));
        text.setFill(Color.RED);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                text.setText("Cash: "+ cash);
            }
        };animationTimer.start();
        return text;
    }
}
