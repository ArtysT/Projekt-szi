package wAIter;

import wAIter.display.Display;
import wAIter.gfx.Assets;
import wAIter.gfx.ImageLoader;
import wAIter.gfx.SpriteSheet;
import wAIter.input.KeyMenager;
import wAIter.states.GameState;
import wAIter.states.MenuState;
import wAIter.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {

    private Display display;

    public String title;
    public int width, height;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //States
    private State gameState;
    private State menuState;

    //Input
    private KeyMenager keyMenager;

    //Handler
    private Handler handler;

    public Game(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        keyMenager = new KeyMenager();
    }

    private void init(){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyMenager);
        Assets.init();

        handler = new Handler(this);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(gameState);
    }

    private void tick(){
        keyMenager.tick();

        if (State.getState() != null)
            State.getState().tick();
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Wczyść ekran
        g.clearRect(0, 0, width, height);
        //Rysuj tutaj

        if (State.getState() != null)
            State.getState().render(g);

        //koniec rysowania
        bs.show();
        g.dispose();
    }

    public void run(){

        init();

        int fps = 60;
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

    public KeyMenager getKeyMenager(){
        return keyMenager;
    }

    public synchronized void start(){
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
