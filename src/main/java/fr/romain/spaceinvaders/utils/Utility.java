package fr.romain.spaceinvaders.utils;

import fr.romain.spaceinvaders.entities.Alien;
import fr.romain.spaceinvaders.entities.Brick;
import javafx.scene.layout.Pane;
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
                if(aliensList.indexOf(a) < 10){ // line 1
                    a.setFill(new ImagePattern(ALIEN_HIGH_2));
                }else if(aliensList.indexOf(a) >= 10 && aliensList.indexOf(a) < 30){ // line 2 & 3
                    a.setFill(new ImagePattern(ALIEN_MIDDLE_2));
                }else if(aliensList.indexOf(a) >= 30){ // line 4 & 5
                    a.setFill(new ImagePattern(ALIEN_BOTTOM_2));
                }
            }else if(imageNumber == 1){
                if(aliensList.indexOf(a) < 10){ // line 1
                    a.setFill(new ImagePattern(ALIEN_HIGH_1));
                }
                if(aliensList.indexOf(a) >= 10 && aliensList.indexOf(a) < 30){ // line 2 & 3
                    a.setFill(new ImagePattern(ALIEN_MIDDLE_1));
                }
                if(aliensList.indexOf(a) >= 30){ // line 4 & 5
                    a.setFill(new ImagePattern(ALIEN_BOTTOM_1));
                }
            }
        }
    }

    public static void aliensHitWalls(List<Brick> walls, List<Alien> aliensList, Pane board){
        Brick db = null;
        for(Alien a : aliensList){
            for(Brick b : walls){
                if(a.getBoundsInParent().intersects(b.getBoundsInParent())){
                    db = b;
                }
            }
            walls.remove(db);
            board.getChildren().remove(db);
        }

    }
}
