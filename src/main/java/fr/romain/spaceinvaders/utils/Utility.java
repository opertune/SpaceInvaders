package fr.romain.spaceinvaders.utils;

import fr.romain.spaceinvaders.entities.Alien;
import javafx.scene.paint.ImagePattern;

import java.util.List;

public class Utility implements Constant, ConstImages {
    public static boolean aliensTouchRightSide(List<Alien> aliens) {
        boolean response = false;
        for (Alien a : aliens) {
            if (a.getX() > FRAME_WIDTH - FRAME_MARGIN - ALIEN_WIDTH - ALIEN_DELTAX) {
                response = true;
                break;
            }
        }
        return response;
    }

    public static boolean aliensTouchLeftSide(List<Alien> aliens) {
        boolean response = false;
        for (Alien a : aliens) {
            if (a.getX() < FRAME_MARGIN) {
                response = true;
                break;
            }
        }
        return response;
    }

    public static void displayAlternateAliens(List<Alien> aliensList, int imageNumber) {
        for (Alien a : aliensList) {
            if (imageNumber == 2){
                if(aliensList.indexOf(a) < 20){ // line 1
                    a.setFill(new ImagePattern(ALIEN_HIGH_2));
                }else if(aliensList.indexOf(a) >= 20 && aliensList.indexOf(a) < 40){ // line 2 & 3
                    a.setFill(new ImagePattern(ALIEN_MIDDLE_2));
                }else if(aliensList.indexOf(a) >= 40 && aliensList.indexOf(a) <= aliensList.size()){ // line 4 & 5
                    a.setFill(new ImagePattern(ALIEN_BOTTOM_2));
                }
            }else if(imageNumber == 1){
                if(aliensList.indexOf(a) < 20){ // line 1
                    a.setFill(new ImagePattern(ALIEN_HIGH_1));
                }
                if(aliensList.indexOf(a) >= 20 && aliensList.indexOf(a) < 40){ // line 2 & 3
                    a.setFill(new ImagePattern(ALIEN_MIDDLE_1));
                }
                if(aliensList.indexOf(a) >= 40 && aliensList.indexOf(a) <= aliensList.size()){ // line 4 & 5
                    a.setFill(new ImagePattern(ALIEN_BOTTOM_1));
                }
            }
        }
    }
}
