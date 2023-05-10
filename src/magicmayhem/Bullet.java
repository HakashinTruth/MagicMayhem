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
    private double bulletspeed;
    private double centerX, centerY, playerRotate, radius;
    private boolean firsthit = false;

    public double getBulletspeed() {
        return bulletspeed;
    }

    public void setBulletspeed(double bulletspeed) {
        this.bulletspeed = bulletspeed;
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public double getPlayerRotate() {
        return playerRotate;
    }

    public void setPlayerRotate(double playerRotate) {
        this.playerRotate = playerRotate;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public boolean isFirsthit() {
        return firsthit;
    }

    public void setFirsthit(boolean firsthit) {
        this.firsthit = firsthit;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRadians() {
        return Radians;
    }

    public void setRadians(double Radians) {
        this.Radians = Radians;
    }

    public Bullet(double radius, Color color, double centerX, double centerY, double playerRotate, double bulletspeed) {
        circ = new Circle(centerX, centerY, radius, color);
        this.bulletspeed = bulletspeed;
        this.radius = radius;
        this.centerY = centerY;
        this.centerX = centerX;
        this.playerRotate = playerRotate;
        circ.setRotate(circ.getRotate() + playerRotate);
        this.color = color;
        if (!firsthit) {
            firstHit();
        }
    }
    public Bullet() {
        circ = new Circle(centerX, centerY, radius, color);
        circ.setRotate(circ.getRotate() + playerRotate);
        if (!firsthit) {
            firstHit();
        }
    }

    public void move() {
        centerX = centerX + (Math.cos(Math.toRadians(playerRotate)) * bulletspeed);
        centerY = centerY + (Math.sin(Math.toRadians(playerRotate)) * bulletspeed);
        circ.setCenterX(centerX);
        circ.setCenterY(centerY);
    }

    public void horizontalColisionmove() {
        if ((0 < circ.getRotate() && circ.getRotate() <= 90)) {
            playerRotate = -playerRotate;
        }
        if ((90 < circ.getRotate() && circ.getRotate() <= 180)) {
            playerRotate = -playerRotate;
        }
        if ((180 < circ.getRotate() && circ.getRotate() <= 270)) {
            playerRotate = -playerRotate;
        }
        if ((270 < circ.getRotate() && circ.getRotate() <= 360)) {
            playerRotate = -playerRotate;
        }
    }

    public void verticalColisionmove() {
        if (0 < circ.getRotate() && circ.getRotate() <= 90) {
            playerRotate = 360 + (180 - playerRotate);

        }
        if (90 < circ.getRotate() && circ.getRotate() <= 180) {
            playerRotate = 360 + (180 - playerRotate);

        }
        if (180 < circ.getRotate() && circ.getRotate() <= 270) {
            playerRotate = (180 - playerRotate);

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
