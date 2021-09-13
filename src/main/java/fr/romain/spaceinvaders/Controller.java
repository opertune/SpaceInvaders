package fr.romain.spaceinvaders;

import fr.romain.spaceinvaders.entities.Ship;
import fr.romain.spaceinvaders.utils.Initialisation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Controller {
    private Ship ship;

    @FXML
    private Pane board;

    @FXML
    private Label lblResult, lblScore;

    @FXML
    void onStartAction() {
        board.requestFocus();
        Initialisation.initShip(ship, board);
    }

    @FXML
    void onStopAction(ActionEvent event) {

    }
}