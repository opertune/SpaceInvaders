package fr.romain.spaceinvaders.entities;

import fr.romain.spaceinvaders.utils.ConstImages;
import fr.romain.spaceinvaders.utils.Constant;
import javafx.scene.paint.ImagePattern;

public class ShipShot extends Entity implements ConstImages, Constant {

    // Constructor
    public ShipShot(double x, double y, double width, double height){
        super(x, y, width, height);
        super.setImgPattern(new ImagePattern(SHIP_SHOT));
        super.setFill(super.getImgPattern());
    }

    // Methods
    public static void shipShotPlacement(ShipShot shipShot, Ship ship){
        shipShot.setX(ship.getX() + 15);
        shipShot.setY(ship.getY());
    }
}
