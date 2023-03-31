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
    boolean dead = false;

    public Player(Random x,Random y,double width, double height, double radius, double playerspeed, double playerrotation, Color color,int screenwidth, int screenheight) {
        rect = new Rectangle(width, height, color);
        Radians = Math.toRadians(rect.getRotate());
        this.speed = playerspeed;
        this.rotation = playerrotation;
        this.speed = playerspeed;
        this.rotation = playerrotation;
        this.color = color;
        rect.setX(x.nextInt((int) screenwidth));
        rect.setY(y.nextInt((int) screenheight));
    }

    void moveup() {
        Radians = Math.toRadians(rect.getRotate());
        rect.setY(rect.getY() + (Math.sin(Radians) * speed));
        rect.setX(rect.getX() + (Math.cos(Radians) * speed));

    }

    void movedown() {
        Radians = Math.toRadians(rect.getRotate());
        rect.setY(rect.getY() - (Math.sin(Radians) * speed));
        rect.setX(rect.getX() - (Math.cos(Radians) * speed));
    }

    void rotate() {
        rect.setRotate((rect.getRotate() + (rotation))%360);
    }

    void antirotate() {
        rect.setRotate((rect.getRotate() + (360-rotation))%360);
    }

//collision methods
    void antimoveup() {
        Radians = Math.toRadians(rect.getRotate());
        rect.setY(rect.getY() - (Math.sin(Radians) * speed));
        rect.setX(rect.getX() - (Math.cos(Radians) * speed));

    }

    void antimovedown() {
        Radians = Math.toRadians(rect.getRotate());
        rect.setY(rect.getY() + (Math.sin(Radians) * speed));
        rect.setX(rect.getX() + (Math.cos(Radians) * speed));

    }

    void colrotate() {
        rect.setRotate(rect.getRotate() - rotation);
    }

    void colantirotate() {
        rect.setRotate(rect.getRotate() + rotation);
    }

    public double getX() {
        return rect.getX();
    }

    public double getY() {
        return rect.getY();
    }

}
