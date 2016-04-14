package wAIter.entities;


import java.awt.*;
import java.util.*;
import java.util.List;


public class Pass extends Entity {


    private boolean ready;
    List<Integer> list = new ArrayList<Integer>();
    private static Queue<Integer> queue = new LinkedList<Integer>();

    public Pass(int x, int y) {
        super(x, y);
        this.type = Type.PASS;
        ready = false;
    }

    public void setOrder(int id){
        list.add(id);
    }

    private void cook(int id){

    }

    public static int getOrder(){
        return queue.poll();
    }

    public void check(){
        if(queue.isEmpty())
            this.ready = false;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x*50, y*50, 50, 50);
        g.setColor(new Color(25, 25, 25));
        g.fillRect(x*50 + 1, y*50 + 1, 48, 48);
        if (ready)
            g.setColor(new Color(255, 225, 50));
        else
            g.setColor(new Color(100, 100, 100));
        g.fillRect(x*50 + 5, y*50 + 5, 41, 41);
        g.drawRect(x*50 + 1, y*50 + 1, 48, 48);


        g.setColor(Color.black);
        g.fillRect(950, 0, 300, 750);
        g.setColor(Color.white);

        Font arial = new Font("Arial", Font.BOLD, 30);
        g.setFont(arial);
        g.drawString("PASS", 1060, 80);
        Font arial2 = new Font("Arial", Font.BOLD, 15);
        g.setFont(arial2);
        g.drawString("IN PROGRES:", 1000, 115);
        g.drawString("READY:", 1000, 415);
        g.drawLine(950, 50, 950, 700);
    }
}
