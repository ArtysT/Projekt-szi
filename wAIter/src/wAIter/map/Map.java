package wAIter.map;


import wAIter.entities.Entity;
import wAIter.entities.Floor;
import wAIter.entities.Table;
import wAIter.entities.Waiter;

import java.awt.*;
import java.util.Random;

import static wAIter.entities.Entity.Type.FLOOR;
import static wAIter.entities.Entity.Type.TABLE;

public class Map {

    public Entity[][] entity = new Entity[25][15];
    public Waiter waiter;
    public Table[] table= new Table[10];
    public Floor floor;

    private int waiterX=0,
                waiterY=0,
                targetId,
                targetX=1,
                targetY=1;

    private boolean backL=false, backR=false, backD=false, backU=false;

    public Map( int tableAmount){
        generateMap(tableAmount);

    }

    public void generateMap(int tableAmount){
        System.out.println("generuje");
        for(int i = 0; i < 25; i++){
            for(int j = 0; j < 15; j++){
                entity[i][j] = new Floor(i, j);
            }
        }
        System.out.println("floor - ok");
        waiter = new Waiter(0, 0);
        entity[0][0] = waiter;
        System.out.println("waiter - ok");


        Random generator = new Random();
        int i = 0;
        while(i < tableAmount){
            int x = generator.nextInt(22) + 2;
            int y = generator.nextInt(12) + 2;
            if(entity[x][y].type == FLOOR){
                table[i] = new Table(x, y);
                entity[x][y] = table[i];
                i++;
            }
        }
        System.out.println("tables - ok");
        targetTable();
        System.out.println("wygenerowana");
    }

    private void targetTable(){
        Random generator = new Random();
        targetId = generator.nextInt(9);
        table[targetId].turnWaiting();
        targetX = table[targetId].getPosX();
        targetY = table[targetId].getPosY();
    }


    public void Move(){
        if(waiterX > targetX && entity[waiterX -1][waiterY].type == FLOOR && backR == false){
            backD = false;
            backU = false;
            backL = false;
            waiter.move(-1, 0);
            entity[waiterX][waiterY] = new Floor(waiterX, waiterY);
            entity[waiterX - 1][waiterY] = waiter;
            waiterX -= 1;
        }
        else if(waiterX < targetX && entity[waiterX +1][waiterY].type == FLOOR && backL == false){
            backD = false;
            backR = false;
            backU = false;
            waiter.move(1, 0);
            entity[waiterX][waiterY] = new Floor(waiterX, waiterY);
            entity[waiterX + 1][waiterY] = waiter;
            waiterX += 1;
        }
        else if(waiterY > targetY && entity[waiterX][waiterY - 1].type == FLOOR && backD == false){
            backU = false;
            backR = false;
            backL = false;
            waiter.move(0, -1);
            entity[waiterX][waiterY] = new Floor(waiterX, waiterY);
            entity[waiterX][waiterY - 1] = waiter;
            waiterY -= 1;
        }
        else if(waiterY < targetY && entity[waiterX][waiterY + 1].type == FLOOR && backU == false){
            backD = false;
            backR = false;
            backL = false;
            waiter.move(0, 1);
            entity[waiterX][waiterY] = new Floor(waiterX, waiterY);
            entity[waiterX][waiterY + 1] = waiter;
            waiterY += 1;
        }
        else if((waiterY-targetY)*(waiterY-targetY) <= 1 && (waiterX-targetX)*(waiterX-targetX) <= 1 ){
            table[targetId].turnServed();
            targetTable();
        }
        else if(entity[waiterX -1][waiterY].type == FLOOR ){
            backL=true;
            waiter.move(-1, 0);
            entity[waiterX][waiterY] = new Floor(waiterX, waiterY);
            entity[waiterX - 1][waiterY] = waiter;
            waiterX -= 1;
        }
        else if(entity[waiterX +1][waiterY].type == FLOOR){
            backR=true;
            waiter.move(1, 0);
            entity[waiterX][waiterY] = new Floor(waiterX, waiterY);
            entity[waiterX + 1][waiterY] = waiter;
            waiterX += 1;
        }
        else if(entity[waiterX][waiterY - 1].type == FLOOR){
            backU=true;
            waiter.move(0, -1);
            entity[waiterX][waiterY] = new Floor(waiterX, waiterY);
            entity[waiterX][waiterY - 1] = waiter;
            waiterY -= 1;
        }
        else if(entity[waiterX][waiterY + 1].type == FLOOR){
            backD=true;
            waiter.move(0, 1);
            entity[waiterX][waiterY] = new Floor(waiterX, waiterY);
            entity[waiterX][waiterY + 1] = waiter;
            waiterY += 1;
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < 25; i++){
            for(int j = 0; j < 15; j++){
                entity[i][j].render(g);
            }
        }
    }

}
