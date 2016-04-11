package wAIter.entities;

import java.awt.*;

public class Table extends Entity {

    private boolean waiting;

    public Table(int x, int y) {
        super(x, y);
        this.waiting = false;
        this.type = Type.TABLE;
    }

    public int getPosX(){
        return x;
    }

    public int getPosY(){
        return y;
    }

    public void turnWaiting(){
        if (!waiting) {
            this.waiting = true;
        }
    }

    public void turnServed(){
        if (waiting)
        {
            this.waiting = false;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(x*50, y*50, 50, 50);
        if (waiting)
            g.setColor(Color.red);
        else
            g.setColor(new Color(42, 179, 231));
        g.fillRoundRect(x*50, y*50, 50, 50, 25, 25);
        g.setColor(Color.black);
        g.drawRect(x*50, y*50, 50, 50);
    }
}
