package fr.romain.spaceinvaders.entities;

import fr.romain.spaceinvaders.utils.Constant;
import fr.romain.spaceinvaders.utils.ConstImages;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Ship extends Entity implements Constant, ConstImages {
    // Members
    private boolean _shipIsShooting;

    // Constructor
    public Ship(double x, double y, double width, double height, Image img) {
        super(x, y, width, height);
        super.setImgPattern(new ImagePattern(img));
        super.setFill(super.getImgPattern());
        this._shipIsShooting = false;
    }

    // Methods
    public double shipMoving(int shipDeltaX) {
        // On renvoie la position bornée du vaisseau
        if(shipDeltaX < 0) {
            if(super.getX() > SHIP_LEFT_FRAME_LIMIT){
                super.setX(super.getX() + shipDeltaX);
            }
        }else if(shipDeltaX > 0){
            if(super.getX() < SHIP_RIGHT_FRAME_LIMIT) {
                super.setX(super.getX() + shipDeltaX);
            }
        }
        return super.getX();
    }

    // Getter & Setter
    public boolean is_shipIsShooting() {
        return _shipIsShooting;
    }

    public void set_shipIsShooting(boolean _shipIsShooting) {
        this._shipIsShooting = _shipIsShooting;
    }
}
