package wAIter.tiles;

import wAIter.gfx.Assets;

import java.awt.image.BufferedImage;

/**
 * Created by thene_000 on 04.04.2016.
 */
public class TableTile extends Tile {
    public TableTile(int id) {
        super(Assets.table, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
