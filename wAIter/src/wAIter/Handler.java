package wAIter;

import wAIter.input.KeyMenager;
import wAIter.maps.Map;

public class Handler {

    private Game game;
    private Map map;

    public Handler(Game game){
        this.game = game;

    }

    public KeyMenager getKeyMenager(){
        return game.getKeyMenager();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

}
