package fr.romain.spaceinvaders.entities;

import fr.romain.spaceinvaders.utils.ConstImages;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.Random;

public class Brick extends Entity implements ConstImages {
    // Members
    private static ArrayList<ImagePattern> rndWall = new ArrayList<>();
    private static Random random = new Random();

    // Constructor
    public Brick(double x, double y, double width, double height, ImagePattern sprite) {
        super(x, y, width, height);
        super.setImgPattern(sprite);
        super.setFill(super.getImgPattern());
    }

    // Methods
    public static ImagePattern setRandomBrick(){
        rndWall.add(IMGP_BRICK_1);
        rndWall.add(IMGP_BRICK_2);
        rndWall.add(IMGP_BRICK_3);
        rndWall.add(IMGP_BRICK_4);

        return rndWall.get(random.nextInt(rndWall.size()));
    }
}
