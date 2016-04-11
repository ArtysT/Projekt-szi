package wAIter.entities;

import java.awt.*;

public class Floor extends Entity {

    public Floor(int x, int y) {
        super(x, y);
        this.type = Type.FLOOR;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(x*50, y*50, 50, 50);
        g.setColor(Color.black);
        g.drawRect(x*50, y*50, 50, 50);
    }
}
