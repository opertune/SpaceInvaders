package fr.romain.spaceinvaders.utils;

import fr.romain.spaceinvaders.entities.Ship;
import javafx.scene.layout.Pane;

public class Initialisation {
    public static void initShip(Ship ship, Pane board){
        ship = new Ship(100, 100, 30, 30);
        board.getChildren().add(ship);
    }
}
