package wAIter;

import wAIter.display.Display;

public class Launcher {

    public static void main(String[] args){
        Game game = new Game("wAIter", 900, 640);
        game.start();
    }

}
