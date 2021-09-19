package fr.romain.spaceinvaders.utils;

public interface Constant {
    // Frame
    int FRAME_WIDTH = 600;
    int FRAME_HEIGHT = 600;
    int FRAME_MARGIN = 50;

    // Ship size
    int SHIP_WIDTH = 39;
    int SHIP_HEIGHT = 24;

    // Ship pos
    int X_POS_INIT_SHIP = (FRAME_WIDTH / 2) - (SHIP_WIDTH / 2);
    int Y_POS_INIT_SHIP = 505;

    // Ship speed
    int SHIP_DELTAX = 5;

    // Ship frame limit
    int SHIP_LEFT_FRAME_LIMIT = 30;
    int SHIP_RIGHT_FRAME_LIMIT = 530;

    // Ship Shot
    int SHIPSHOT_WIDTH = 10;
    int SHIPSHOT_HEIGHT = 10;
    int SHIPSHOT_DELTAY = -10;

    // Brick
    int BRICK_WIDTH = 10;
    int BRICK_HEIGHT = 10;

    // Alien
    int ALIEN_WIDTH = 33;
    int ALIEN_HEIGHT = 25;

    int X_POS_INIT_ALIEN = 40 + FRAME_MARGIN;
    int Y_POS_INIT_ALIEN = 50;
    int X_SPACE_ALIEN = 10;
    int Y_SPACE_ALIEN = 10;

    int ALIEN_DELTAX = 5;
    int ALIEN_DELTAY = 20;

    int ALIEN_SHOT_WIDTH = 5;
    int ALIEN_SHOT_HEIGHT = 20;
    int ALIEN_SHOT_DELTAY = -2;
}

