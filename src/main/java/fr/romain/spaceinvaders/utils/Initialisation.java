package fr.romain.spaceinvaders.utils;

import fr.romain.spaceinvaders.entities.Brick;
import fr.romain.spaceinvaders.entities.Ship;
import fr.romain.spaceinvaders.entities.ShipShot;
import javafx.scene.layout.Pane;

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
}
