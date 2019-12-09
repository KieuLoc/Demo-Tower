package sample;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public abstract class Tower extends ObjectManager {
    protected boolean existence;
    protected int i,j;
    protected int damageDealt;
    protected double range;

    public Tower(){

    }

    public Tower(int i, int j, Group root, Player player){
        this.player=player;
        this.i=i;
        this.j=j;
        this.root=root;
        existence=true;
    }

    public void render() {
    }

    public void remove(){
    }
    public void ReadyAim(double enemyX, double enemyY, int towerIndex, int enemyIndex){
    }

    public void shoot() {
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    public double getRange() {
        return range;
    }

}

