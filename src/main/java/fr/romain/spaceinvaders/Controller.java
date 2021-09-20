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
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class Controller implements Constant, ConstImages, ConstSounds {
    private Ship ship;
    private AnimationTimer timer;
    private int shipDeltaX;
    private ShipShot shipshot;
    private List<Brick> walls;
    private List<Alien> aliensList = new ArrayList<>();
    private long movingAliensCount = 0;
    private int score = 0;
    private List<ShipShot> alienShot = new ArrayList<>();
    private boolean initStartButton = false;
    private Ship saucer;
    private Timer saucerTimer = new Timer();


    @FXML
    private ImageView explod;

    @FXML
    private ImageView shipExplod;

    @FXML
    private Pane board;

    @FXML
    private Label lblResult, lblScore;

    @FXML
    private Slider sldVolume;

    @FXML
    void onStartAction() {
        if (!initStartButton) {
            board.requestFocus();
            initGame();
            Initialisation.initShip(ship, board);
            Initialisation.initSaucer(saucer, board);
            Initialisation.initShipShot(shipshot, board);
            Initialisation.initWalls(80, 400, 80, walls, board);
            Initialisation.initAliens(aliensList, board);
            Initialisation.initAliensShot(alienShot, board);
            timer.start();
            moveSaucer();
            initStartButton = true;
        }
    }

    private void initGame() {
        ship = new Ship(X_POS_INIT_SHIP, Y_POS_INIT_SHIP, SHIP_WIDTH, SHIP_HEIGHT, SHIP);
        saucer = new Ship(X_POS_INIT_SAUCER, Y_POS_INIT_SAUCER, SAUCER_WIDTH, SAUCER_HEIGHT, SAUCER);

        shipshot = new ShipShot(-10, -10, SHIPSHOT_WIDTH, SHIPSHOT_HEIGHT, SHIP_SHOT);
        walls = new LinkedList<>();
        movingAliensCount = 0;
        lblScore.setText(String.valueOf(score));

        for (int i = 0; i < NB_SHOT; i++) {
            alienShot.add(new ShipShot(-10, -10, ALIEN_SHOT_WIDTH, ALIEN_SHOT_HEIGHT, ALIEN_SHOT));
        }
    }

    @FXML
    void onStopAction(ActionEvent event) {
        clearBoard();
        timer.stop();
        initStartButton = false;
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
                    Initialisation.initSound(shipShotSound, sldVolume);
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

                try {
                    for (ShipShot s : alienShot) {
                        if (!s.isShooting()) {
                            Initialisation.initSound("src/main/resources/fr/romain/spaceinvaders/sounds/alienShotSound" + (new Random().nextInt(4) + 1) + ".wav", sldVolume);
                            s.setVisible(true);
                            Alien rndAlien = aliensList.get(new Random().nextInt(50));
                            s.setX(rndAlien.getX() + 15);
                            s.setY(rndAlien.getY());
                        }
                    }
                    for (ShipShot s : alienShot) {
                        if (!s.isShooting()) {
                            s.setShooting(true);
                        }
                    }
                    aliensHandleShot();
                } catch (Exception e) {

                }
            }
        };
    }

    private void clearBoard() {
        board.getChildren().removeAll(aliensList);
        board.getChildren().removeAll(ship);
        board.getChildren().removeAll(shipshot);
        board.getChildren().removeAll(alienShot);
        board.getChildren().removeAll(walls);
        alienShot.clear();
        aliensList.clear();
        walls.clear();
        shipExplod.setY(-30);
        lblResult.setText("");
        lblScore.setText("0");
        score = 0;
    }

    private void handleShip() {
        shipMoveHorizontal(shipDeltaX);
    }

    private void shipMoveHorizontal(int shipDeltaX) {
        ship.setX(ship.shipMoving(shipDeltaX));
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
            Initialisation.initSound(brickDestructionSound, sldVolume);
            walls.remove(brick);
            board.getChildren().remove(brick);
        }

        // Collision avec un alien
        Alien deletedAlien = null;
        for (Alien alien : aliensList) {
            if (alien.getBoundsInParent().intersects(shipshot.getBoundsInParent())) {
                Initialisation.initSound(alienDeadSound, sldVolume);
                // On replace le tir hors du bord
                shipshot.setX(-10);
                shipshot.setY(-10);

                // On autorise un nouveau tir
                ship.set_shipIsShooting(false);

                // Déplace l'imageview qui contient le gif de l'explosion à l'emplacement de l'alien mort
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
                    ship.set_shipIsShooting(true);
                }
            }
        }
        // Supprime l'alien touché
        if (deletedAlien != null) {
            board.getChildren().remove(aliensList.get(aliensList.indexOf(deletedAlien)));
            aliensList.remove(deletedAlien);
            if (aliensList.size() < 5) {
                board.getChildren().remove(alienShot.get(0));
                alienShot.remove(0);
            }
        }
    }

    private void aliensHandleShot() {
        // On ne veut pas que les aliens puissent tirer en rafale
        for (ShipShot s : alienShot) {
            if (s.getY() >= 515) {
                s.setShooting(false);
                s.setVisible(false);
            } else if (s.getY() < 535) {
                s.setY(s.getY() - ALIEN_SHOT_DELTAY);
            }
            alienShotCollisions(s);
        }
    }

    private void alienShotCollisions(ShipShot s) {
        // Collision avec une brique
        Brick brick = null;
        for (Brick b : walls) {
            if (b.getBoundsInParent().intersects(s.getBoundsInParent())) {
                s.setX(-10);
                s.setY(-10);
                s.setShooting(false);
                brick = b;
            }
        }
        if (brick != null) {
            Initialisation.initSound(brickDestructionSound, sldVolume);
            walls.remove(brick);
            board.getChildren().remove(brick);
        }

        // Collision avec le joueur
        if(s.getBoundsInParent().intersects(ship.getBoundsInParent())){
            shipExplod.setX(ship.getX());
            shipExplod.setY(ship.getY());
            shipExplod.setImage(SHIP_EXPLOD);
            board.getChildren().remove(ship);
            lblResult.setTextFill(Color.RED);
            lblResult.setText("LOSE !");
            timer.stop();
        }
    }

    // Move saucer
    private void moveSaucer(){
        saucerTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (saucer.getX() <= 650) {
                    AnimationTimer t = new AnimationTimer() {
                        @Override
                        public void handle(long now) {
                            if (saucer.getX() >= 650) {
                                this.stop();
                            }
                            saucer.setX(saucer.getX() + SAUCER_DELTAX);
                        }
                    };
                    t.start();
                } else if (saucer.getX() >= -50) {
                    AnimationTimer t = new AnimationTimer() {
                        @Override
                        public void handle(long now) {
                            if (saucer.getX() <= -50) {
                                this.stop();
                            }
                            saucer.setX(saucer.getX() - SAUCER_DELTAX);
                        }
                    };
                    t.start();
                }
            }
        },5000, SAUCER_DELAY);
    }
}