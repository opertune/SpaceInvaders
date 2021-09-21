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

    Image ALIEN_HIGH_1 = new Image(PATH+"alienHigh1.png");
    Image ALIEN_HIGH_2 = new Image(PATH+"alienHigh2.png");
    Image ALIEN_MIDDLE_1 = new Image(PATH+"alienMiddle1.png");
    Image ALIEN_MIDDLE_2 = new Image(PATH+"alienMiddle2.png");
    Image ALIEN_BOTTOM_1 = new Image(PATH+"alienBottom1.png");
    Image ALIEN_BOTTOM_2 = new Image(PATH+"alienBottom2.png");

    Image ALIEN_SHOT = new Image(PATH + "aliensShoot2.png");

    Image ALIEN_EXPLOD = new Image(PATH + "explod.gif");
    Image SHIP_EXPLOD = new Image(PATH + "explodShip.gif");

    Image SAUCER = new Image(PATH + "saucer.png");
    Image SAUCER100POINTS = new Image(PATH + "saucer100.png");
}
