package fr.romain.spaceinvaders;

import fr.romain.spaceinvaders.entities.Ship;
import fr.romain.spaceinvaders.entities.ShipShot;
import fr.romain.spaceinvaders.utils.Constant;
import fr.romain.spaceinvaders.utils.Initialisation;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class Controller implements Constant {
    private Ship ship;
    private AnimationTimer timer;
    private int shipDeltaX;
    private ShipShot shipshot;

    public Controller(){
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                handleShip();
                if(ship.is_shipIsShooting()){
                    handleShipShot();
                }
            }
        };
    }

    @FXML
    private Pane board;

    @FXML
    private Label lblResult, lblScore;

    @FXML
    void onStartAction() {
        board.requestFocus();
        initGame();
        Initialisation.initShip(ship, board);
        Initialisation.initShipShot(shipshot, board);

        timer.start();
    }

    @FXML
    void onStopAction(ActionEvent event) {
        timer.stop();
    }

    @FXML
    void onKeyPressed(KeyEvent event) {
        switch (event.getCode()){
            case A: shipDeltaX = -SHIP_DELTAX; handleShip(); break;
            case D: shipDeltaX = SHIP_DELTAX; handleShip(); break;
            case SPACE: if(!ship.is_shipIsShooting()){ ship.set_shipIsShooting(true); ShipShot.shipShotPlacement(shipshot, ship); }; break;
        }
    }

    @FXML
    void onKeyReleased(KeyEvent event) {
        shipDeltaX = 0;
    }

    private void handleShip(){
        shipMoveHorizontal(shipDeltaX);
    }

    private void handleShipShot(){
        // On ne veut pas que le vaisseau puisse tirer en rafale
        if(shipshot.getY() <= -20){
            ship.set_shipIsShooting(false);
        }else if(shipshot.getY() >= 20){
            shipshot.setY(shipshot.getY() + SHIPSHOT_DELTAY);
        }
    }

    private void shipMoveHorizontal(int shipDeltaX){
        ship.setX(ship.shipMoving(shipDeltaX));
    }

    private void initGame(){
        ship = new Ship(X_POS_INIT_SHIP, Y_POS_INIT_SHIP, SHIP_WIDTH, SHIP_HEIGHT);
        shipshot = new ShipShot(-10, -10, SHIPSHOT_WIDTH, SHIPSHOT_HEIGHT);
    }
}