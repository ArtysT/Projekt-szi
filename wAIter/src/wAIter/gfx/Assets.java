package wAIter.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 64, height = 64;

    public static BufferedImage player, floor, wall, table;

    public static void init(){
        player = ImageLoader.loadImage("/textures/player.png");
        floor = ImageLoader.loadImage("/textures/floor.png");
        wall = ImageLoader.loadImage("/textures/wall.jpg");
        table = ImageLoader.loadImage("/textures/table.png");


    }

}
