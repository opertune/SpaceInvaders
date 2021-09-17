package fr.romain.spaceinvaders.utils;

import fr.romain.spaceinvaders.entities.Alien;
import fr.romain.spaceinvaders.entities.Brick;
import fr.romain.spaceinvaders.entities.Ship;
import fr.romain.spaceinvaders.entities.ShipShot;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    public static void initAliens(List<Alien> aliensList, Pane board){
        for (int i = 1; i <= 5; i++){
            for (int j = 0; j < 10; j++){
                if(i < 1){
                    aliensList.add(new Alien(X_POS_INIT_ALIEN+(ALIEN_WIDTH+X_SPACE_ALIEN)*j, Y_POS_INIT_ALIEN, ALIEN_WIDTH, ALIEN_HEIGHT, ALIEN_HIGH_1));
                }else if(i < 3){
                    aliensList.add(new Alien(X_POS_INIT_ALIEN+(ALIEN_WIDTH+X_SPACE_ALIEN)*j, Y_POS_INIT_ALIEN*i, ALIEN_WIDTH, ALIEN_HEIGHT, ALIEN_MIDDLE_1));
                }else {
                    aliensList.add(new Alien(X_POS_INIT_ALIEN+(ALIEN_WIDTH+X_SPACE_ALIEN)*j, Y_POS_INIT_ALIEN*i, ALIEN_WIDTH, ALIEN_HEIGHT, ALIEN_BOTTOM_1));
                }
            }
        }

        // Display Aliens in board
        for (Alien a : aliensList){
            board.getChildren().add(a);
        }
    }

    public static void initSound(String soundPath){
        File sound = new File(soundPath);
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = control.getMinimum();
            control.setValue(range * (1 - 60/100.0f));
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }

    }
}
