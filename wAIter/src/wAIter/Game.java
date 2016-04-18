package wAIter;

import java.awt.*;
import java.awt.image.BufferStrategy;

import wAIter.display.Display;
import wAIter.entities.Pass;
import wAIter.map.Map;

public class Game implements Runnable {

    private Display display;
    private Map map;
    public int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
    }

    private void init(){
        display = new Display(title, width, height);
        map = new Map(20);
    }

    private void tick(){
        map.Move();
        Pass.cook();
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        //Draw Here!

        map.render(g);

        //g.setColor(Color.red);
        //g.fillRect(10, 50, 50, 70);
        //g.setColor(Color.green);
        //g.fillRect(0, 0, 10, 10);

        //End Drawing!
        bs.show();
        g.dispose();
    }

    public void run(){

        init();

        int fps = 6;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();

        while (running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;

            if (delta >= 1){
                tick();
                render();
                delta--;
            }
        }

        stop();

    }

    public synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}