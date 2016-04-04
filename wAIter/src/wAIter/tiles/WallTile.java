package wAIter.tiles;

import wAIter.gfx.Assets;

import java.awt.image.BufferedImage;

public class WallTile extends Tile {
    public WallTile(int id) {
        super(Assets.wall, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
