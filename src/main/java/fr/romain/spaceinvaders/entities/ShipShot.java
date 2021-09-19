package fr.romain.spaceinvaders.entities;

import fr.romain.spaceinvaders.utils.ConstImages;
import fr.romain.spaceinvaders.utils.Constant;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class ShipShot extends Entity implements ConstImages, Constant {
    // Members
    private boolean isShooting;

    // Constructor
    public ShipShot(double x, double y, double width, double height, Image img){
        super(x, y, width, height);
        super.setImgPattern(new ImagePattern(img));
        super.setFill(super.getImgPattern());
        this.isShooting = false;
    }

    // Methods
    public static void shipShotPlacement(ShipShot shipShot, Ship ship){
        shipShot.setX(ship.getX() + 15);
        shipShot.setY(ship.getY());
    }

    // Getter & Setter
    public boolean isShooting() {
        return isShooting;
    }

    public void setShooting(boolean shooting) {
        isShooting = shooting;
    }
}
