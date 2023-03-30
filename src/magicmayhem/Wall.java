/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicmayhem;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author 9706
 */
public class Wall  extends Rectangle{
    public boolean isVertical;

    Wall(int x, int y, int width, int height, boolean isVertical) {
       super(x, y, width, height);
                this.isVertical = isVertical;
    }
}
