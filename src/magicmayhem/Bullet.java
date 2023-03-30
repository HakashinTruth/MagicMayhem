/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicmayhem;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author 9706
 */
public class Bullet extends Sprite {

    private final Circle circ;
    double bulletspeed;
    public double centerX, centerY, playerRotate, radius;
    boolean firsthit = false;

    public Bullet(double radius, Color color, double centerX, double centerY, double playerRotate, double bulletspeed) {
        circ = new Circle(centerX, centerY, radius, color);
        this.bulletspeed = bulletspeed;
        this.radius = radius;
        this.centerY = centerY;
        this.centerX = centerX;
        this.playerRotate=playerRotate;
        circ.setRotate(playerRotate);
        this.color = color;
        if (!firsthit) {
            firstHit();
        }
    }

    public Bullet() {
        circ = new Circle(centerX, centerY, radius, color);
    }

    public void move() {
        centerX = centerX + (Math.cos(Math.toRadians(playerRotate)) * bulletspeed);
        centerY = centerY + (Math.sin(Math.toRadians(playerRotate)) * bulletspeed);
        circ.setCenterX(centerX);
        circ.setCenterY(centerY);
    }

    public void horizontalColisionmove() {
        System.out.println("hor");
        if ((0 < circ.getRotate() && circ.getRotate() <= 90)) {
           circ.setRotate(-circ.getRotate());
        }
        if ((90 < circ.getRotate() && circ.getRotate() <= 180)) {
             circ.setRotate(-circ.getRotate());
        }
        if ((180 < circ.getRotate() && circ.getRotate() <= 270)) {
             circ.setRotate(-circ.getRotate());
        }
        if ((270 < circ.getRotate() && circ.getRotate() <= 360)) {
             circ.setRotate(-circ.getRotate());
        }
    }

    public void verticalColisionmove() {
         System.out.println("ver");
        if (0 < circ.getRotate() && circ.getRotate() <= 90) {
            playerRotate = 360 + (180 - playerRotate);

        }
        if (90 < circ.getRotate() && circ.getRotate() <= 180) {
            playerRotate = 360 + (180 - playerRotate);

        }
        if (180 < circ.getRotate() && circ.getRotate() <= 270) {
            playerRotate =  (180 - playerRotate);

        }
        if (270 < circ.getRotate() && circ.getRotate() <= 360) {
            playerRotate = 360 + (180 - playerRotate);

        }
    }

    @Override
    double getX() {
        return circ.getCenterX();
    }

    @Override
    double getY() {
        return circ.getCenterY();

    }

    public Circle getCircle() {
        return circ;
    }

    private boolean firstHit() {
        centerX = centerX + (Math.cos(Math.toRadians(playerRotate)) * 50);
        centerY = centerY + (Math.sin(Math.toRadians(playerRotate)) * 50);
        circ.setCenterX(centerX);
        circ.setCenterY(centerY);
        firsthit = true;
        return firsthit;

    }
}
