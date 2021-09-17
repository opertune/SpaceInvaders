package fr.romain.spaceinvaders.utils;

import fr.romain.spaceinvaders.entities.Alien;
import javafx.scene.paint.ImagePattern;

public class Utility implements Constant, ConstImages {
    public static boolean aliensTouchRightSide(Alien[][] aliens){
        boolean response = false;
        for(int column = 0; column < 10; column++){
            for (int line = 0; line < 5; line++){
                if(aliens[line][column].getX() > FRAME_WIDTH - FRAME_MARGIN - ALIEN_WIDTH - ALIEN_DELTAX){
                    response = true;
                    break;
                }
            }
        }
        return response;
    }

    public static boolean aliensTouchLeftSide(Alien[][] aliens){
        boolean response = false;
        for(int column = 0; column < 10; column++){
            for (int line = 0; line < 5; line++){
                if(aliens[line][column].getX() < FRAME_MARGIN){
                    response = true;
                    break;
                }
            }
        }
        return response;
    }

    public static void displayAlternateAliens(Alien[][] aliens, int imageNumber){
        for(int column = 0; column < 10; column++){
            if(imageNumber == 1){
                aliens[0][column].setFill(new ImagePattern(ALIEN_HIGH_1));
            }else if(imageNumber == 2){
                aliens[0][column].setFill(new ImagePattern(ALIEN_HIGH_2));
            }
            for (int line = 1; line < 3; line++){
                if(imageNumber == 1){
                    aliens[line][column].setFill(new ImagePattern(ALIEN_MIDDLE_1));
                }else if(imageNumber == 2){
                    aliens[line][column].setFill(new ImagePattern(ALIEN_MIDDLE_2));
                }
            }
            for (int line = 3; line < 5; line++){
                if(imageNumber == 1){
                    aliens[line][column].setFill(new ImagePattern(ALIEN_BOTTOM_1));
                }else if(imageNumber == 2){
                    aliens[line][column].setFill(new ImagePattern(ALIEN_BOTTOM_2));
                }
            }
        }
    }
}
