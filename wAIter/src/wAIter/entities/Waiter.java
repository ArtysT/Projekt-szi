package wAIter.entities;


import java.awt.*;

public class Waiter extends Entity {

    private boolean note, tray;

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
        g.setColor(Color.black);
        g.fillRect(x*50, y*50, 50, 50);
        g.setColor(new Color(25, 25, 25));
        g.fillRect(x*50 + 6, y*50 + 6, 38, 38);
        g.setColor(new Color(255, 225, 50));
        g.fillRect(x*50 + 10 , y*50 + 10, 31, 31);
        g.drawRect(x*50 + 6, y*50 + 6, 38, 38);
    }
}
