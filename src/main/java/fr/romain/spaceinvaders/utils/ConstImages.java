package fr.romain.spaceinvaders.utils;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public interface ConstImages {
    String PATH = "File:./src/main/resources/fr/romain/spaceinvaders/images/";
    Image SHIP = new Image(PATH+"vaisseau.png");
    Image SHIP_SHOT = new Image(PATH+"ship1Shoot.png");

    Image IMG_BRICK_1 = new Image(PATH+"wall1.png");
    Image IMG_BRICK_2 = new Image(PATH+"wall2.png");
    Image IMG_BRICK_3 = new Image(PATH+"wall3.png");
    Image IMG_BRICK_4 = new Image(PATH+"wall4.png");

    ImagePattern IMGP_BRICK_1 = new ImagePattern(IMG_BRICK_1);
    ImagePattern IMGP_BRICK_2 = new ImagePattern(IMG_BRICK_2);
    ImagePattern IMGP_BRICK_3 = new ImagePattern(IMG_BRICK_3);
    ImagePattern IMGP_BRICK_4 = new ImagePattern(IMG_BRICK_4);
}
