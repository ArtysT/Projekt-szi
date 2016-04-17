package wAIter.map;


public class Point{
    int f = 100;
    int g = 100;
    int h = 100;
    int heuristicCost = 0; //Heuristic cost
    int finalCost = 0; //G+H
    int i, j;

    Point parent;

    Point(int i, int j){
        this.i = i;
        this.j = j;
    }
}
