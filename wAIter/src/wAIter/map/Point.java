package wAIter.map;


import java.awt.*;

public class Point{
    int f = 100;
    int g = 100;
    int h = 100;
    int i, j;

    Point parent;

    Point(int i, int j){
        this.i = i;
        this.j = j;
    }

    public void render(Graphics g) {
        g.setColor(new Color(100, 100, 100));
        g.fillRect(i*50 + 10, j*50 + 10, 31, 31);
    }
}
