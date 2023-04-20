/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicmayhem;

import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author 9706
 */
class Player extends Sprite {

    public Rectangle rect;
    public Rectangle turrent;
    boolean dead = false;

    public Player(Random x, Random y, double width, double height, double radius, double playerspeed, double playerrotation, Color color, int screenwidth, int screenheight) {
        //player body
        rect = new Rectangle(width, height, color);
        Radians = Math.toRadians(rect.getRotate());
        this.speed = playerspeed;
        this.rotation = playerrotation;
        this.speed = playerspeed;
        this.rotation = playerrotation;
        this.color = color;
        rect.setX(x.nextInt((int) screenwidth));
        rect.setY(y.nextInt((int) screenheight));
        //turret body
        double turretWidth = width + 10;
        double turretHeight = height / 3;
        turrent = new Rectangle(turretWidth, turretHeight, color);
        turrent.setX((rect.getX() - turretWidth / 2 + rect.getWidth() / 2) + 10);
        turrent.setY(rect.getY() - turretHeight / 2 + rect.getHeight() / 2);
        turrent.setRotate(rect.getRotate());
    }

    void moveup() {
        Radians = Math.toRadians(rect.getRotate());
        rect.setY(rect.getY() + (Math.sin(Radians) * speed));
        rect.setX(rect.getX() + (Math.cos(Radians) * speed));
        //turrent
        turrent.setX(turrent.getX() + (Math.cos(Radians) * speed));
        turrent.setY(turrent.getY() + (Math.sin(Radians) * speed));

    }

    void movedown() {
        Radians = Math.toRadians(rect.getRotate());
        rect.setY(rect.getY() - (Math.sin(Radians) * speed));
        rect.setX(rect.getX() - (Math.cos(Radians) * speed));
        //turrent
        turrent.setX(turrent.getX() - (Math.cos(Radians) * speed));
        turrent.setY(turrent.getY() - (Math.sin(Radians) * speed));
    }

    void rotate() {
        rect.setRotate((rect.getRotate() + (rotation)) % 360);
        //turrent
        turrent.setRotate(rect.getRotate());
    }

    void antirotate() {
        rect.setRotate((rect.getRotate() + (360 - rotation)) % 360);
        //turrent
        turrent.setRotate(rect.getRotate());
    }

//collision methods
    void antimoveup() {
        Radians = Math.toRadians(rect.getRotate());
        rect.setY(rect.getY() - (Math.sin(Radians) * speed));
        rect.setX(rect.getX() - (Math.cos(Radians) * speed));
        //turrent
        turrent.setX(turrent.getX() - (Math.cos(Radians) * speed));
        turrent.setY(turrent.getY() - (Math.sin(Radians) * speed));

    }

    void antimovedown() {
        Radians = Math.toRadians(rect.getRotate());
        rect.setY(rect.getY() + (Math.sin(Radians) * speed));
        rect.setX(rect.getX() + (Math.cos(Radians) * speed));
        //turrent
        turrent.setX(turrent.getX() + (Math.cos(Radians) * speed));
        turrent.setY(turrent.getY() + (Math.sin(Radians) * speed));

    }

    void colrotate() {
        rect.setRotate(rect.getRotate() - rotation);
        //turrent
        turrent.setRotate(rect.getRotate());
    }

    void colantirotate() {
        rect.setRotate(rect.getRotate() + rotation);
        //turrent
        turrent.setRotate(rect.getRotate());
    }

    public double getX() {
        return rect.getX();
    }

    public double getY() {
        return rect.getY();
    }

}
