package fr.romain.spaceinvaders;

import fr.romain.spaceinvaders.entities.*;
import fr.romain.spaceinvaders.utils.ConstImages;
import fr.romain.spaceinvaders.utils.ConstSounds;
import fr.romain.spaceinvaders.utils.Constant;
import fr.romain.spaceinvaders.utils.Initialisation;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Controller implements Constant, ConstImages, ConstSounds {
    private Ship ship;
    private AnimationTimer timer;
    private int shipDeltaX;
    private ShipShot shipshot;
    private List<Brick> walls;
    private List<Alien> aliensList = new ArrayList<>();
    private long movingAliensCount = 0;
    private int score = 0;

    @FXML
    private ImageView explod;

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
        Initialisation.initWalls(80, 400, 80, walls, board);
        Initialisation.initAliens(aliensList, board);

        timer.start();
    }

    @FXML
    void onStopAction(ActionEvent event) {
        timer.stop();
    }

    @FXML
    void onKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case A:
                shipDeltaX = -SHIP_DELTAX;
                handleShip();
                break;
            case D:
                shipDeltaX = SHIP_DELTAX;
                handleShip();
                break;
            case SPACE:
                if (!ship.is_shipIsShooting()) {
                    Initialisation.initSound(shipShotSound);
                    ship.set_shipIsShooting(true);
                    ShipShot.shipShotPlacement(shipshot, ship);
                }
                break;
        }
    }

    @FXML
    void onKeyReleased(KeyEvent event) {
        shipDeltaX = 0;
    }

    public Controller() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                movingAliensCount++;
                handleShip();
                if (ship.is_shipIsShooting()) {
                    handleShipShot();
                }
                if (movingAliensCount % (100 - (10L * Alien.getSpeed())) == 0) {
                    Alien.aliensMoving(aliensList);
                    movingAliensCount = 0;
                }
            }
        };
    }

    private void initGame() {
        ship = new Ship(X_POS_INIT_SHIP, Y_POS_INIT_SHIP, SHIP_WIDTH, SHIP_HEIGHT);
        shipshot = new ShipShot(-10, -10, SHIPSHOT_WIDTH, SHIPSHOT_HEIGHT);
        walls = new LinkedList<>();
        //aliens = new Alien[5][10];
        movingAliensCount = 0;
        lblScore.setText(String.valueOf(score));
    }

    private void handleShip() {
        shipMoveHorizontal(shipDeltaX);
    }

    private void handleShipShot() {
        // On ne veut pas que le vaisseau puisse tirer en rafale
        if (shipshot.getY() <= -20) {
            ship.set_shipIsShooting(false);
        } else if (shipshot.getY() >= -20) {
            shipshot.setY(shipshot.getY() + SHIPSHOT_DELTAY);
        }
        shipShotCollisions();
    }

    private void shipMoveHorizontal(int shipDeltaX) {
        ship.setX(ship.shipMoving(shipDeltaX));
    }

    private void shipShotCollisions() {
        // Collision avec une brique
        Brick brick = null;
        for (Brick b : walls) {
            if (b.getBoundsInParent().intersects(shipshot.getBoundsInParent())) {
                shipshot.setX(-10);
                shipshot.setY(-10);
                ship.set_shipIsShooting(false);
                brick = b;
            }
        }
        if (brick != null) {
            Initialisation.initSound(brickDestructionSound);
            walls.remove(brick);
            board.getChildren().remove(brick);
        }

        // Collision avec un alien
        Alien deletedAlien = null;
        for (Alien alien : aliensList) {
            if (alien.getBoundsInParent().intersects(shipshot.getBoundsInParent())) {
                Initialisation.initSound(alienDeadSound);
                // On replace le tir hors du bord
                shipshot.setX(-10);
                shipshot.setY(-10);
                // On autorise un nouveau tir
                ship.set_shipIsShooting(false);

                // Déplace l'imageview qui contient le gif de l'éplosion à l'emplacement de l'alien mort
                explod.setLayoutX(alien.getX());
                explod.setLayoutY(alien.getY());
                explod.setImage(new Image("File:./src/main/resources/fr/romain/spaceinvaders/images/explod.gif"));
                deletedAlien = alien;

                // Augmente et affiche le score
                score++;
                lblScore.setText(String.valueOf(score));
                // Si tout les aliens sont mort -> Fin du jeu
                if (score == 50) {
                    timer.stop();
                    lblResult.setTextFill(Color.web("#009402"));
                    lblResult.setText("WIN !");
                }
            }
        }
        // Supprime l'alien touché
        if(deletedAlien != null){
            board.getChildren().remove(aliensList.get(aliensList.indexOf(deletedAlien)));
            aliensList.remove(deletedAlien);
        }
    }


}