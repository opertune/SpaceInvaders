package fr.romain.spaceinvaders.entities;

import fr.romain.spaceinvaders.utils.*;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class Alien extends Entity implements Constant, ConstImages, ConstSounds {
    // Members
    private static boolean goRight = true;
    private static boolean alienPosition = true;
    private static int speed = 1;
    private static List<Boolean> alienIsShooting = new ArrayList<>();

    // Constructor
    public Alien(double x, double y, double width, double height, Image image) {
        super(x, y, width, height);
        super.setImg(image);
        super.setImgPattern(new ImagePattern(super.getImg()));
        super.setFill(super.getImgPattern());
        for(int i = 0; i < 5; i++){
            alienIsShooting.add(false);
        }
    }

    // Methods
    public static void aliensMoving(List<Alien> aliens) {
        if (goRight) { // Déplacement vers la droite
            for (Alien a : aliens) {
                a.setX(a.getX() + ALIEN_DELTAX);
            }
            aliensMovingIntoBoard(aliens);
        } else { // Déplacement vers la gauche
            for (Alien a : aliens) {
                a.setX(a.getX() - ALIEN_DELTAX);
            }
            aliensMovingIntoBoard(aliens);
        }
        int imgNb;
        if (alienPosition) {
            imgNb = 2;
            alienPosition = false;
        } else {
            imgNb = 1;
            alienPosition = true;
        }
        Utility.displayAlternateAliens(aliens, imgNb);

    }

    public static void aliensMovingIntoBoard(List<Alien> aliens) {
        // Les aliens descendent et se dirigent dans l'autre sens
        if (Utility.aliensTouchRightSide(aliens)) {
            for (Alien a : aliens) {
                a.setY(a.getY() + ALIEN_DELTAY);
            }
            goRight = false;
            if (Alien.getSpeed() < 10) {
                Alien.setSpeed(Alien.getSpeed() + 1);
            }
        } else if (Utility.aliensTouchLeftSide(aliens)) {
            for (Alien a : aliens) {
                a.setY(a.getY() + ALIEN_DELTAY);
            }
            goRight = true;
            if (Alien.getSpeed() < 10) {
                Alien.setSpeed(Alien.getSpeed() + 1);
            }
        }
    }


    // Getter & Setter
    public static int getSpeed() {
        return speed;
    }

    public static void setSpeed(int speed) {
        Alien.speed = speed;
    }

    public static List<Boolean> getAlienIsShooting() {
        return alienIsShooting;
    }

    public static void setAlienIsShooting(List<Boolean> alienIsShooting) {
        Alien.alienIsShooting = alienIsShooting;
    }
}
