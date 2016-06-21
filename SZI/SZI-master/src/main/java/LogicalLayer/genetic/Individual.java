package LogicalLayer.genetic;


import java.util.List;
import java.util.Random;

public class Individual {
    private List<TableGenetic> tableGeneticList;
    private int classifier;
    private boolean[][] tableMap;

    public Individual(List<TableGenetic> tableGeneticList) {
        this.tableGeneticList = tableGeneticList;
        this.classifier = calculateClassifier();
    }

    public void mutation() {
        int mutationPoint = new Random().nextInt(this.getTableGeneticList().size());
        TableGenetic mutatedTableGenetic = this.getTableGeneticList().get(mutationPoint);

        int randomValue = new Random().nextInt(2);
        if (randomValue == 0) {
            int randX = new Random().nextInt(17);
            int randY = new Random().nextInt(13);
            this.getTableGeneticList().set(mutationPoint, new TableGenetic(randX, randY));
        } else {
            this.getTableGeneticList().set(mutationPoint,
                    new TableGenetic(mutatedTableGenetic.getX(), mutatedTableGenetic.getY()));
        }
        this.classifier = calculateClassifier();
    }

    private int calculateClassifier() {
        int tmpClassifier = 10000;
        int numberOfRichTable = 0;

        tableMap = new boolean[17][13];
        for (TableGenetic tableGenetic : tableGeneticList) {
            if (tableMap[tableGenetic.getX()][tableGenetic.getY()]) {
                return 0;
            } else {
                tableMap[tableGenetic.getX()][tableGenetic.getY()] = true;
            }
        }

        for (int i = 0; i < tableGeneticList.size(); i++) {

            if ((tableGeneticList.get(i).getX() == 0 && tableGeneticList.get(i).getY() == 0)
                    || (tableGeneticList.get(i).getX() == 1 && tableGeneticList.get(i).getY() == 1)
                    || (tableGeneticList.get(i).getX() == 0 && tableGeneticList.get(i).getY() == 1)
                    || (tableGeneticList.get(i).getX() == 1 && tableGeneticList.get(i).getY() == 0)) {
                return 0;
            }
            int x = tableGeneticList.get(i).getX();
            int y = tableGeneticList.get(i).getY();
            if (x + 1 < tableMap.length && tableMap[x + 1][y]) {
                tmpClassifier -= 100;
            }
            if (x - 1 >= 0 && tableMap[x - 1][y]) {
                tmpClassifier -= 100;
            }
            if (y + 1 < tableMap[0].length && tableMap[x][y + 1]) {
                tmpClassifier -= 100;
            }
            if (y - 1 >= 0 && tableMap[x][y - 1]) {
                tmpClassifier -= 100;
            }
            if (x + 2 < tableMap.length && tableMap[x + 2][y]) {
                tmpClassifier -= 100;
            }
            if (x - 2 >= 0 && tableMap[x - 2][y]) {
                tmpClassifier -= 100;
            }
            if (y + 2 < tableMap[0].length && tableMap[x][y + 2]) {
                tmpClassifier -= 100;
            }
            if (y - 2 >= 0 && tableMap[x][y - 2]) {
                tmpClassifier -= 100;
            }
            if (x + 3 < tableMap.length && tableMap[x + 3][y]) {
                tmpClassifier -= 100;
            }
            if (x - 3 >= 0 && tableMap[x - 3][y]) {
                tmpClassifier -= 100;
            }
            if (y + 3 < tableMap[0].length && tableMap[x][y + 3]) {
                tmpClassifier -= 100;
            }
            if (y - 3 >= 0 && tableMap[x][y - 3]) {
                tmpClassifier -= 100;
            }
            if (x + 1 < tableMap.length && y + 1 < tableMap[0].length && tableMap[x + 1][y + 1]) {
                tmpClassifier -= 100;
            }
            if (x - 1 >= 0 && y + 1 < tableMap[0].length && tableMap[x - 1][y + 1]) {
                tmpClassifier -= 100;
            }
            if (x - 1 >= 0 && y - 1 >= 0 && tableMap[x - 1][y - 1]) {
                tmpClassifier -= 100;
            }
            if (x + 1 < tableMap.length && y - 1 >= 0 && tableMap[x + 1][y - 1]) {
                tmpClassifier -= 100;
            }
            if (x + 2 < tableMap.length && y + 1 < tableMap[0].length && tableMap[x + 2][y + 1]) {
                tmpClassifier -= 100;
            }
            if (x - 2 >= 0 && y + 1 < tableMap[0].length && tableMap[x - 2][y + 1]) {
                tmpClassifier -= 100;
            }
            if (x - 2 >= 0 && y - 1 >= 0 && tableMap[x - 2][y - 1]) {
                tmpClassifier -= 100;
            }
            if (x + 2 < tableMap.length && y - 1 >= 0 && tableMap[x + 2][y - 1]) {
                tmpClassifier -= 100;
            }
            if (x + 1 < tableMap.length && y + 2 < tableMap[0].length && tableMap[x + 1][y + 2]) {
                tmpClassifier -= 100;
            }
            if (x - 1 >= 0 && y + 2 < tableMap[0].length && tableMap[x - 1][y + 2]) {
                tmpClassifier -= 100;
            }
            if (x - 1 >= 0 && y - 2 >= 0 && tableMap[x - 1][y - 2]) {
                tmpClassifier -= 100;
            }
            if (x + 1 < tableMap.length && y - 2 >= 0 && tableMap[x + 1][y - 2]) {
                tmpClassifier -= 100;
            }
            if (x + 2 < tableMap.length && y + 2 < tableMap[0].length && tableMap[x + 2][y + 2]) {
                tmpClassifier -= 100;
            }
            if (x - 2 >= 0 && y + 2 < tableMap[0].length && tableMap[x - 2][y + 2]) {
                tmpClassifier -= 100;
            }
            if (x - 2 >= 0 && y - 2 >= 0 && tableMap[x - 2][y - 2]) {
                tmpClassifier -= 100;
            }
            if (x + 2 < tableMap.length && y - 2 >= 0 && tableMap[x + 2][y - 2]) {
                tmpClassifier -= 100;
            }
        }

        return tmpClassifier;
    }

    public List<TableGenetic> getTableGeneticList() {
        return tableGeneticList;
    }

    public int getClassifier() {
        return classifier;
    }

    public boolean[][] getTableMap() {
        return tableMap;
    }
}
