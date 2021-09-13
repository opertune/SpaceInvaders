package fr.romain.spaceinvaders.entities;

import fr.romain.spaceinvaders.utils.Images;
import javafx.scene.paint.ImagePattern;

public class Ship extends Entity{
    // Members
    private boolean _shipIsShooting;

    // Constructor
    public Ship(double x, double y, double width, double height) {
        super(x, y, width, height);
        super.setImgPattern(new ImagePattern(Images.ship));
        super.setFill(super.getImgPattern());
        this._shipIsShooting = false;
    }




}
