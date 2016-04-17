package wAIter.map;


import wAIter.entities.*;


import java.awt.*;
import java.lang.Object;
import java.util.PriorityQueue;
import java.util.Random;

import static wAIter.entities.Entity.Type.FLOOR;
import static wAIter.entities.Entity.Type.TABLE;

public class Map {

    private Entity[][] entity = new Entity[19][15];
    private Waiter waiter;
    private Table[] table= new Table[20];
    private Point[][] grid = new Point[19][15];
    Point cur;


    private int waiterX=9,
                waiterY=7,
                targetId,
                targetX=1,
                targetY=1;

    private boolean backL=false, backR=false, backD=false, backU=false;

    public Map( int tableAmount){
        generateMap(tableAmount);

    }

    private void generateMap(int tableAmount){
        System.out.println("generuje");
        for(int i = 0; i < 19; i++){
            for(int j = 0; j < 15; j++){
                if(i == 0 || i == 18 || j==0 || j==14)
                    entity[i][j] = new wAIter.entities.Object(i, j);
                else{
                    entity[i][j] = new Floor(i, j);
                    grid[i][j] = new Point(i, j);
                }
            }
        }
        System.out.println("floor - ok");
        waiter = new Waiter(9, 7);
        System.out.println("waiter - ok");


        Pass pass = new Pass(9, 0);
        entity[9][0] = pass;
        System.out.println("pass - ok");


        Random generator = new Random();
        int i = 0;
        while(i < tableAmount){
            int x = generator.nextInt(8) + 1;
            int y = generator.nextInt(6) + 1;
            if(entity[x][y].type == FLOOR && entity[x+1][y].type != TABLE && entity[x-1][y].type != TABLE && entity[x][y+1].type != TABLE && entity[x][y-1].type != TABLE && entity[x+1][y+1].type != TABLE && entity[x-1][y-1].type != TABLE && entity[x+1][y-1].type != TABLE && entity[x-1][y+1].type != TABLE){
                table[i] = new Table(x, y);
                entity[x][y] = table[i];
                grid[x][y] = null;
                i++;
                table[i] = new Table(18-x, 14-y);
                entity[18-x][14- y] = table[i];
                grid[18-x][14- y] = null;
                i++;
                table[i] = new Table(x, 14-y);
                entity[x][14-y] = table[i];
                grid[x][14-y] = null;
                i++;
                table[i] = new Table(18-x, y);
                entity[18-x][y] = table[i];
                grid[18-x][y] = null;
                i++;

            }
        }
        System.out.println("tables - ok");
        targetTable();
        System.out.println("wygenerowana");
    }
    PriorityQueue<Point> open;
    private void targetTable(){
        closed = new boolean[19][15];
        open = new PriorityQueue<>((Object o1, Object o2) -> {
            Point c1 = (Point)o1;
            Point c2 = (Point)o2;

            return c1.f<c2.f?-1:
                    c1.f>c2.f?1:0;
        });
        grid[targetX][targetY] = null;
        if(entity[targetX][targetY].type == TABLE){
            targetX = 9;
            targetY = 0;
        }
        else {
            Random generator = new Random();
            targetId = generator.nextInt(20);
            table[targetId].turnWaiting();
            targetX = table[targetId].getPosX();
            targetY = table[targetId].getPosY();}

        grid[targetX][targetY] = new Point(targetX, targetY);
        grid[targetX][targetY].g = 0;
        grid[targetX][targetY].h = Math.abs(targetY-waiterX) + Math.abs(targetY-waiterY);
        grid[targetX][targetY].f = grid[targetX][targetY].g + grid[targetX][targetY].h;
        AStar();
        cur = grid[waiterX][waiterY];
    }

    boolean closed[][];


    void checkAndUpdateCost(Point current, Point t, int cost){
        if(t == null || closed[t.i][t.j])return;
        t.h = Math.abs(t.i-waiterX) + Math.abs(t.j-waiterY);
        int t_final_cost = t.h+cost;

        boolean inOpen = open.contains(t);
        if(!inOpen || t_final_cost<t.f){
            t.g = cost;
            t.f = t_final_cost;
            t.parent = current;
            if(!inOpen)open.add(t);
        }
    }

    public void AStar(){

        //add the start location to open list.
        open.add(grid[targetX][targetY]);

        Point current;

        while(true){
            current = open.poll();
            if(current==null)break;
            closed[current.i][current.j]=true;

            if(current.equals(grid[waiterX][waiterY])){
                return;
            }

            Point t;
            if(current.i-1>=0){
                t = grid[current.i-1][current.j];
                checkAndUpdateCost(current, t, current.g+1);
            }

            if(current.j-1>=0){
                t = grid[current.i][current.j-1];
                checkAndUpdateCost(current, t, current.g+1);
            }

            if(current.j+1<grid[0].length){
                t = grid[current.i][current.j+1];
                checkAndUpdateCost(current, t, current.g+1);
            }

            if(current.i+1<grid.length){
                t = grid[current.i+1][current.j];
                checkAndUpdateCost(current, t, current.g+1);
            }
        }
    }

    public void Move(){

        if(cur.parent!=null){
            System.out.println(cur.f);
            waiterX = cur.i;
            waiterY = cur.j;
            waiter.move(cur.i, cur.j);
            cur = cur.parent;
        }
        else{
            table[targetId].turnServiced();
            targetTable();
        }

        /*
        if(waiterX > targetX && entity[waiterX -1][waiterY].type == FLOOR && !backR){
            backL = false;
            backD = false;
            backU = false;
            waiter.move(-1, 0);
            waiterX -= 1;
        }
        else if(waiterX < targetX && entity[waiterX +1][waiterY].type == FLOOR && !backL){
            backR = false;
            backD = false;
            backU = false;
            waiter.move(1, 0);
            waiterX += 1;
        }
        else if(waiterY > targetY && entity[waiterX][waiterY - 1].type == FLOOR && !backD){
            backU = false;
            backR = false;
            backL = false;
            waiter.move(0, -1);
            waiterY -= 1;
        }
        else if(waiterY < targetY && entity[waiterX][waiterY + 1].type == FLOOR && !backU){
            backD=false;
            backR = false;
            backL = false;
            waiter.move(0, 1);
            waiterY += 1;
        }
        else if((waiterY-targetY)*(waiterY-targetY) <= 1 && (waiterX-targetX)*(waiterX-targetX) <= 1 ){
            table[targetId].turnServiced();
            targetTable();
        }
        else if(entity[waiterX -1][waiterY].type == FLOOR && !backR){
            backL=true;
            backD = false;
            backU = false;
            waiter.move(-1, 0);
            waiterX -= 1;
        }
        else if(entity[waiterX +1][waiterY].type == FLOOR && !backL){
            backR=true;
            backD = false;
            backU = false;
            waiter.move(1, 0);
            waiterX += 1;
        }
        else if(entity[waiterX][waiterY - 1].type == FLOOR && !backD){
            backU=true;
            backR = false;
            backL = false;
            waiter.move(0, -1);
            waiterY -= 1;
        }
        else if(entity[waiterX][waiterY + 1].type == FLOOR && !backD){
            backD=true;
            backR = false;
            backL = false;
            waiter.move(0, 1);
            waiterY += 1;
        }
*/
    }

    public void render(Graphics g){
        for(int i = 0; i < 19; i++){
            for(int j = 0; j < 15; j++){
                entity[i][j].render(g);
            }
        }
        waiter.render(g);
    }

}
