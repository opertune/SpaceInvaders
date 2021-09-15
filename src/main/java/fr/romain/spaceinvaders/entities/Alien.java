package fr.romain.spaceinvaders.entities;

import fr.romain.spaceinvaders.utils.Constant;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Alien extends Entity implements Constant {
    public Alien(double x, double y, double width, double height, Image image) {
        super(x, y, width, height);
        super.setImg(image);
        super.setImgPattern(new ImagePattern(super.getImg()));
        super.setFill(super.getImgPattern());
    }
}
