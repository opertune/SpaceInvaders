package fr.romain.spaceinvaders.entities;

import fr.romain.spaceinvaders.utils.ConstImages;
import fr.romain.spaceinvaders.utils.Constant;
import fr.romain.spaceinvaders.utils.Utility;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.lang.reflect.Array;

public class Alien extends Entity implements Constant, ConstImages {
    // Members
    private static boolean goRight = true;
    private static boolean alienPosition = true;
    private static int speed = 1;

    // Constructor
    public Alien(double x, double y, double width, double height, Image image) {
        super(x, y, width, height);
        super.setImg(image);
        super.setImgPattern(new ImagePattern(super.getImg()));
        super.setFill(super.getImgPattern());
    }

    // Methods
    public static void aliensMoving(Alien[][] aliens){
        if(goRight){ // Déplacement vers la droite
            for(int column = 0; column < 10; column++){
                for (int line = 0; line < 5; line++){
                    aliens[line][column].setX(aliens[line][column].getX() + ALIEN_DELTAX);
                }
            }
        }else{ // Déplacement vers la gauche
            for(int column = 0; column < 10; column++){
                for (int line = 0; line < 5; line++){
                    aliens[line][column].setX(aliens[line][column].getX() - ALIEN_DELTAX);
                }
            }
        }
        int imgNb;
        if (alienPosition) {
            imgNb = 2;
            alienPosition = false;
        }else {
            imgNb = 1;
            alienPosition = true;
        }
        Utility.displayAlternateAliens(aliens, imgNb);
        aliensMovingIntoBoard(aliens);
    }

    public static void aliensMovingIntoBoard(Alien[][] aliens){
        // Les aliens descendent et se dirigent dans l'autre sens
        if(Utility.aliensTouchRightSide(aliens)){
            for(int column = 0; column < 10; column++){
                for (int line = 0; line < 5; line++){
                    aliens[line][column].setY(aliens[line][column].getY() + ALIEN_DELTAY);
                }
            }
            goRight = false;
            if(Alien.getSpeed() < 10){
                Alien.setSpeed(Alien.getSpeed()+1);
            }
        }else if(Utility.aliensTouchLeftSide(aliens)){
            for(int column = 0; column < 10; column++){
                for (int line = 0; line < 5; line++){
                    aliens[line][column].setY(aliens[line][column].getY() + ALIEN_DELTAY);
                }
            }
            goRight = true;
            if(Alien.getSpeed() < 10){
                Alien.setSpeed(Alien.getSpeed()+1);
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
}
