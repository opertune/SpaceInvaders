package fr.romain.spaceinvaders.utils;

import fr.romain.spaceinvaders.entities.Ship;
import fr.romain.spaceinvaders.entities.ShipShot;
import javafx.scene.layout.Pane;

public class Initialisation{
    public static void initShip(Ship ship, Pane board){
        board.getChildren().add(ship);
    }

    public static void initShipShot(ShipShot shipshot, Pane board){
        board.getChildren().add(shipshot);
    }
}
