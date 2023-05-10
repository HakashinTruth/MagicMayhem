/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicmayhem;

import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author 9706
 */
public class PowerUp {

    Circle powerShape;
    Label bulletSpeedPower, LazerPower, ImortalityPower;
    int powerRadius, xPos, yPos  ;
    Color color;
    boolean bulletspedactive, lazeractive, imortalactive = false;

    public PowerUp(int xPos, int yPos, int powerRadius, Color color ) {
        powerShape = new Circle(xPos, yPos, powerRadius, color);
        this.powerRadius = powerRadius;
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
 

 
    }

      void bulletspeedBoost() {
        Bullet bullet = new Bullet();
        if (bulletspedactive) {
            bullet.setSpeed(211);
        }
    }

      void imortalBoost() {
        lazeractive = true;
    }

      void lazerBoost() {
        imortalactive = true;
    }

}
