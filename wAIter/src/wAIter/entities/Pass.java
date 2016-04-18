package wAIter.entities;


import java.awt.*;
import java.util.*;
import java.util.List;


public class Pass extends Entity {


    public static boolean ready;
    private static Queue<Integer> list = new LinkedList<Integer>();
    private static Queue<Integer> queue = new LinkedList<Integer>();

    public Pass(int x, int y) {
        super(x, y);
        this.type = Type.PASS;
        ready = false;
    }

    public void setOrder(int id){
        list.add(id);
    }

    public static void cook(){
        Random generator = new Random();
        if(!list.isEmpty()) {
            if (generator.nextInt(35) == 1) {
                queue.add(list.poll());
                if(!ready){
                    ready = true;
                }
            }
        }
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
            g.setColor(new Color(170, 145, 70));
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
        int i = 130;
        for(Integer l : list) {
            g.drawString(l.toString(), 1050, i);
            i += 15;
        }
        g.drawString("READY:", 1000, 415);
        int j = 430;
        for(Integer q : queue) {
            g.drawString(q.toString(), 1050, j);
            j += 15;
        }
        g.drawLine(950, 50, 950, 700);
    }
}
