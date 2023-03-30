/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicmayhem;

import javafx.scene.paint.Color;

/**
 *
 * @author 9706
 */
public abstract class Sprite {

    Color color;
    double speed, rotation;
    double Radians;

    abstract double getX();

    abstract double getY();

}
