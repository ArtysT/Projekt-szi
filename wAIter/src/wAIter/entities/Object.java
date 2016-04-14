package wAIter.entities;

import java.awt.*;

public class Object extends Entity {
    public Object(int x, int y) {
        super(x, y);
        this.type = Type.OBJECT;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x*50, y*50, 50, 50);
    }
}
