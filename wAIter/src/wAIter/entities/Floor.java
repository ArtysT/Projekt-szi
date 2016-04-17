package wAIter.entities;

import java.awt.*;
import java.lang.*;
import java.lang.Object;

public class Floor extends Entity implements Comparable{

    public int heuristicCost = 0;
    public int finalCost = 0;
    public Floor parent;

    public Floor(int x, int y) {
        super(x, y);
        this.type = Type.FLOOR;
    }

    @Override
    public void render(Graphics g) {
        //g.setColor(new Color(25, 25, 25));
        //g.fillRect(x*50, y*50, 50, 50);
        //g.setColor(Color.black);
        //g.drawRect(x*50, y*50, 50, 50);
        g.setColor(Color.black);
        g.fillRect(x*50, y*50, 50, 50);
        g.setColor(new Color(25, 25, 25));
        g.fillRect(x*50 + 4, y*50 + 4, 42, 42);
        g.setColor(new Color(100, 100, 100));
        g.drawRect(x*50 + 4, y*50 + 4, 42, 42);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
