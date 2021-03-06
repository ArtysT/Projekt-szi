package wAIter.entities;


import java.awt.*;

public abstract class Entity {

    public int x;
    public int y;
    public enum Type{
        WAITER,
        TABLE,
        FLOOR,
        PASS,
        OBJECT
    }

    public Type type;

    public Entity(int x, int y){
        this.x = x;
        this.y = y;
    }

    public abstract void render(Graphics g);
}
