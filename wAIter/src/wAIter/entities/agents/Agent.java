package wAIter.entities.agents;

import wAIter.Handler;
import wAIter.entities.Entity;
import wAIter.tiles.Tile;

public abstract class Agent extends Entity {

    public static final float DEFLAUT_SPEED = 3.0f;
    public static final int DEFLAUT_AGENT_WIDTH = 64,
                            DEFLAUT_AGENT_HEIGHT = 64;


    protected float speed;
    protected float xMove, yMove;

    public Agent(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFLAUT_SPEED;
        xMove = 0;
        yMove = 0;

    }

    public void move(){
        moveX();
        moveY();
    }

    public void moveX(){
        if(xMove > 0){//Ruch w prawo
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){
                x += xMove;
            }
            else {
                x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }
        }
        else if (xMove < 0){//Ruch w lewo
            int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;

            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){
                x += xMove;
            }
            else {
                x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
        }
    }

    public void moveY(){
        if(yMove < 0){//Up
            int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)){
                y += yMove;
            }
            else {
                y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }
        }else if(yMove > 0){//Down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)){
                y += yMove;
            }
            else {
                y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y){
        return handler.getMap().getTile(x, y).isSolid();
    }

    //GETTERS SETTERS

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }
}
