package wAIter.entities;


import java.awt.*;

public class Pass extends Entity {
    public Pass(int x, int y) {
        super(x, y);
        this.type = Type.PASS;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(x*50, y*50, 50, 50);
        g.setColor(Color.black);
        g.drawRect(x*50, y*50, 50, 50);
    }
}
