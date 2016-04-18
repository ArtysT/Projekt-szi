package wAIter.entities;

import java.awt.*;

public class Table extends Entity {

    public boolean waiting, hungry;

    public Table(int x, int y) {
        super(x, y);
        this.waiting = false;
        this.hungry = false;
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
            this.hungry = true;
        }
    }

    public void turnServiced(){
        if (waiting)
        {
            this.waiting = false;
        }
    }

    public void turnServed(){
        if (!waiting)
        {
            this.hungry = false;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x*50, y*50, 50, 50);
        g.setColor(new Color(25, 25, 25));
        g.fillRect(x*50 + 2, y*50 + 2, 46, 46);
        if (waiting)
            g.setColor(new Color(200, 100, 100));
        else if (hungry)
            g.setColor(new Color(50, 110, 125));
        else
            g.setColor(new Color(100, 200, 215));
        g.fillRect(x*50 + 6, y*50 + 6, 39, 39);
        g.drawRect(x*50 + 2, y*50 + 2, 46, 46);
    }
}
