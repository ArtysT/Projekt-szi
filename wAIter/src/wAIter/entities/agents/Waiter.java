package wAIter.entities.agents;

import wAIter.Game;
import wAIter.Handler;
import wAIter.gfx.Assets;

import java.awt.*;

public class Waiter extends Agent {

    public Waiter(Handler handler, float x, float y) {
        super(handler, x, y, Agent.DEFLAUT_AGENT_WIDTH, Agent.DEFLAUT_AGENT_HEIGHT);
        this.handler = handler;

        bounds.x = 22;
        bounds.y = 0;
        bounds.width = 22;
        bounds.height = 60;

    }

    @Override
    public void tick() {
        getInput();
        move();
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if (handler.getKeyMenager().up)
            yMove = -speed;
        if (handler.getKeyMenager().down)
            yMove = speed;
        if (handler.getKeyMenager().left)
            xMove = -speed;
        if (handler.getKeyMenager().right)
            xMove = speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) x , (int) y, width, height, null);


    }
}
