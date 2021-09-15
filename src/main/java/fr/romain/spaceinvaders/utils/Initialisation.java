package fr.romain.spaceinvaders.utils;

import fr.romain.spaceinvaders.entities.Alien;
import fr.romain.spaceinvaders.entities.Brick;
import fr.romain.spaceinvaders.entities.Ship;
import fr.romain.spaceinvaders.entities.ShipShot;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Initialisation implements ConstImages, Constant{
    public static void initShip(Ship ship, Pane board){
        board.getChildren().add(ship);
    }

    public static void initShipShot(ShipShot shipshot, Pane board){
        board.getChildren().add(shipshot);
    }

    public static void initWalls(int x, int y, int nextX, List<Brick> walls, Pane board){
        int xDecal;
        for (int i = 0; i<4; i++){
            xDecal = 120*i;
            wall(x, y, nextX, walls, xDecal);
        }

        board.getChildren().addAll(walls);
    }

    private static void wall(int x, int y, int nextX, List<Brick> walls, int xDecal){
        for (int i = 0; i <= 3; i++){
            for (int j = 0; j <= 6; j++){
                walls.add(new Brick(x+xDecal, y, BRICK_WIDTH, BRICK_HEIGHT, Brick.setRandomBrick()));
                x+=10;
            }
            x = nextX;
            y += 10;
        }
    }

    public static void initAliens(Alien[][] aliens, Pane board){
        for (int colonne = 0; colonne < 10; colonne++){
            aliens[0][colonne] = new Alien(X_POS_INIT_ALIEN+(ALIEN_WIDTH+X_SPACE_ALIEN)*colonne, Y_POS_INIT_ALIEN, ALIEN_WIDTH, ALIEN_HEIGHT, ALIEN_HIGH_1);
            for(int line = 1; line < 3; line++){
                aliens[line][colonne] = new Alien(X_POS_INIT_ALIEN+(ALIEN_WIDTH+X_SPACE_ALIEN)*colonne, Y_POS_INIT_ALIEN+(ALIEN_HEIGHT+Y_SPACE_ALIEN)*line, ALIEN_WIDTH, ALIEN_HEIGHT, ALIEN_MIDDLE_1);
            }
            for(int line = 3; line < 5; line ++){
                aliens[line][colonne] = new Alien(X_POS_INIT_ALIEN+(ALIEN_WIDTH+X_SPACE_ALIEN)*colonne, Y_POS_INIT_ALIEN+(ALIEN_HEIGHT+Y_SPACE_ALIEN)*line, ALIEN_WIDTH, ALIEN_HEIGHT, ALIEN_BOTTOM_1);
            }
        }

        // Display Aliens in board
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 10; j++){
                board.getChildren().add(aliens[i][j]);
            }
        }
    }
}
