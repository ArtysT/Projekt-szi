package wAIter.entities;


import java.awt.*;

public class Waiter extends Entity {
    public Waiter(int x, int y) {
        super(x, y);
        this.type = Type.WAITER;
    }

    public void move(int x, int y){
        this.x += x;
        this.y += y;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(x*50, y*50, 50, 50);
        g.setColor(new Color(252, 211, 61));
        g.fillRoundRect(x*50, y*50, 50, 50, 25, 25);
        g.setColor(Color.black);
        g.drawRect(x*50, y*50, 50, 50);
    }
}
