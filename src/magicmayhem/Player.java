/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicmayhem;

import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author 9706
 */
class Player extends Sprite {

    private Rectangle rect;
    private Circle turrent;
    private boolean dead = false;
    private double width, height;
    private double offset = 25;

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public Circle getTurrent() {
        return turrent;
    }

    public void setTurrent(Circle turrent) {
        this.turrent = turrent;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getOffset() {
        return offset;
    }

    public void setOffset(double offset) {
        this.offset = offset;
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

    public Player(Random x, Random y, double width, double height, double radius, double playerspeed, double playerrotation, Color color, int screenwidth, int screenheight) {
        //player body
        rect = new Rectangle(width, height, color);
        Radians = Math.toRadians(rect.getRotate());
        this.speed = playerspeed;
        this.rotation = playerrotation;
        this.speed = playerspeed;
        this.rotation = playerrotation;
        this.color = color;
        this.width = width / 2;
        this.height = height / 2;
        rect.setX(x.nextInt((int) screenwidth));
        rect.setY(y.nextInt((int) screenheight));
        //turret body
        turrent = new Circle(10, Color.WHITE);
        move();
    }

    void moveup() {
        Radians = Math.toRadians(rect.getRotate());
        rect.setY(rect.getY() + (Math.sin(Radians) * speed));
        rect.setX(rect.getX() + (Math.cos(Radians) * speed));
        move();
    }

    void movedown() {
        Radians = Math.toRadians(rect.getRotate());
        rect.setY(rect.getY() - (Math.sin(Radians) * speed));
        rect.setX(rect.getX() - (Math.cos(Radians) * speed));
        move();
    }

    void rotate() {
        rect.setRotate((rect.getRotate() + (rotation)) % 360);
        move();
    }

    void antirotate() {
        rect.setRotate((rect.getRotate() + (360 - rotation)) % 360);
        move();
    }

//collision methods
    void antimoveup() {
        Radians = Math.toRadians(rect.getRotate());
        rect.setY(rect.getY() - (Math.sin(Radians) * speed));
        rect.setX(rect.getX() - (Math.cos(Radians) * speed));
        move();
    }

    void antimovedown() {
        Radians = Math.toRadians(rect.getRotate());
        rect.setY(rect.getY() + (Math.sin(Radians) * speed));
        rect.setX(rect.getX() + (Math.cos(Radians) * speed));
        move();
    }

    void colrotate() {
        rect.setRotate(rect.getRotate() - rotation);
        move();
    }

    void colantirotate() {
        rect.setRotate(rect.getRotate() + rotation);
        move();
    }

    public double getX() {
        return rect.getX();
    }

    public double getY() {
        return rect.getY();
    }

    private void move() {
        Radians = Math.toRadians(rect.getRotate());
        turrent.setCenterX(rect.getX() + width);
        turrent.setCenterY(rect.getY() + height);
        turrent.setCenterX(rect.getX() + (Math.cos(Radians) * offset) + width);
        turrent.setCenterY(rect.getY() + (Math.sin(Radians) * offset) + height);
    }

}
