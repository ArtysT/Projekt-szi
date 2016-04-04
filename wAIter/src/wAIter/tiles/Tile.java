package wAIter.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    //STATIC STUFF

    public static Tile[] tiles = new Tile[256];
    public static Tile floorTile = new FloorTile(0);
    public static Tile WallTile = new WallTile(1);
    public static Tile TableTile = new TableTile(2);

    //CLASS

    public static final int TILE_WIDTH = 64,
                            TILE_HEIGHT = 64;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick(){

    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public boolean isSolid(){
        return false;
    }

    public int getId(){
        return id;
    }

}
