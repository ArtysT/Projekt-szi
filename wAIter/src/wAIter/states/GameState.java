package wAIter.states;

import wAIter.Game;
import wAIter.Handler;
import wAIter.entities.agents.Waiter;
import wAIter.gfx.Assets;
import wAIter.maps.Map;
import wAIter.tiles.Tile;

import java.awt.*;

public class GameState extends State {

    private Waiter waiter;
    private Map map;

    public GameState(Handler handler){
        super(handler);
        map = new Map("res/maps/map1.txt");
        handler.setMap(map);
        waiter = new Waiter(handler, 100, 100);
    }

    @Override
    public void tick() {
        map.tick();
        waiter.tick();
    }

    @Override
    public void render(Graphics g) {
        map.render(g);
        waiter.render(g);
    }
}
