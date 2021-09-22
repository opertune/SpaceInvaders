package fr.romain.spaceinvaders.utils;

import fr.romain.spaceinvaders.entities.*;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class Initialisation implements ConstImages, Constant{
    public static void initShip(Ship ship, Pane board){
        board.getChildren().add(ship);
    }

    public static void initSaucer(Ship saucer, Pane board){
        board.getChildren().add(saucer);
    }

    public static void initShipShot(ShipShot shipshot, Pane board){
        board.getChildren().add(shipshot);
    }

    public static void initAliensShot(List<ShipShot> shipShot, Pane board){
        for(ShipShot s : shipShot){
            board.getChildren().add(s);
        }
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
                if(i <= 1){
                    aliensList.add(new Alien(X_POS_INIT_ALIEN+(ALIEN_WIDTH+X_SPACE_ALIEN)*j, Y_POS_INIT_ALIEN, ALIEN_WIDTH, ALIEN_HEIGHT, ALIEN_HIGH_1));
                }else if(i <= 3){
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

    public static void initSound(String soundPath, Slider volume){
        File sound = new File(soundPath);
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));

            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = control.getMinimum();

            control.setValue((float) (range * (1 - volume.getValue()/100.0f)));
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void animateImg(ImageView imgvLogo, int fromY, int toY){
        TranslateTransition animationImage = new TranslateTransition(Duration.millis(800), imgvLogo);
        animationImage.setFromY(fromY);
        animationImage.setToY(toY);
        animationImage.setInterpolator(Interpolator.EASE_OUT);
        animationImage.play();
    }
}
