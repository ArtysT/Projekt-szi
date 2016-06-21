package LogicalLayer.genetic;


import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GeneticAlgorithm {
    private Queue<Individual> individuals = new PriorityQueue<>((x, y) -> {
        return y.getClassifier() - x.getClassifier();
    });

    public List<TableGenetic> findTheBestIndividual(int maxNumberOfIterations) throws IOException {
        System.out.println("finding the best individual started");
        generateInitialPopulation();

        for (int i = 0; i < maxNumberOfIterations; i++) {
            //System.out.println(i);
            Individual firstIndividual = individuals.remove();
            Individual secondIndividual = individuals.remove();
            IndividualPair newIndividualPair = crossover(new IndividualPair(firstIndividual, secondIndividual));
            Individual newFirstIndividual = newIndividualPair.getFirst();
            Individual newSecondIndividual = newIndividualPair.getSecond();

            int firstMutationProbability = new Random().nextInt(50);
            if (firstMutationProbability == 0) {
                newFirstIndividual.mutation();
            }
            int secondMutationProbability = new Random().nextInt(50);
            if (secondMutationProbability == 0) {
                newSecondIndividual.mutation();
            }

            individuals.add(newFirstIndividual);
            individuals.add(newSecondIndividual);
            individuals.add(firstIndividual);
            individuals.add(secondIndividual);
        }

        Individual bestIndividual = individuals.peek();
        System.out.println("finding the best individual ended");
        System.out.println("best classifier: " + bestIndividual.getClassifier());
        boolean map[][] = bestIndividual.getTableMap();
        FileWriter fw = new FileWriter("resources//genmap.txt");
        fw.write("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n");
        fw.write("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n");
        for (int i = 0; i < map[0].length ; i++) {
            fw.write("0 0 ");
            for (int j = 0; j < map.length ; j++) {
                if(map[j][i])
                    fw.write("1 ");
                else
                    fw.write("0 ");
            }
            fw.write("0 0\n");
        }
        fw.write("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n");
        fw.write("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0");
        fw.close();
        return bestIndividual.getTableGeneticList();
    }

    private void generateInitialPopulation() {
        int numberOfIndividuals = 1000;
        for (int i = 0; i < numberOfIndividuals; i++) {
            Individual newIndividual = new Individual(generateTableGeneticList());
            individuals.add(newIndividual);
        }
    }

    private List<TableGenetic> generateTableGeneticList() {
        List<TableGenetic> tableGeneticList = new ArrayList<>();
//        int nuberOfTable = new Random().nextInt(Map.getHEIGHT() + Map.getWIDTH()) + 5;
        int nuberOfTable = 16;
        for (int i = 0; i < nuberOfTable; i++) {
            int randX = new Random().nextInt(17);
            int randY = new Random().nextInt(13);

            tableGeneticList.add(new TableGenetic(randX, randY));
        }
        return tableGeneticList;
    }

    private IndividualPair crossover(IndividualPair individualPair) {
        List<TableGenetic> firstTableGeneticList = individualPair.getFirst().getTableGeneticList();
        List<TableGenetic> secondTableGeneticList = individualPair.getSecond().getTableGeneticList();

        int firstCrossoverPoint = new Random().nextInt(firstTableGeneticList.size());
//        int secondCrossoverPoint = new Random().nextInt(secondTableGeneticList.size());

        List<TableGenetic> part1FirstTableGeneticList = new ArrayList<>(firstTableGeneticList.subList(0, firstCrossoverPoint));
        List<TableGenetic> part2FirstTableGeneticList =
                new ArrayList<>(firstTableGeneticList.subList(firstCrossoverPoint, firstTableGeneticList.size()));
        List<TableGenetic> part1SecondTableGeneticList = new ArrayList<>(secondTableGeneticList.subList(0, firstCrossoverPoint));
        List<TableGenetic> part2SecondTableGeneticList =
                new ArrayList<>(secondTableGeneticList.subList(firstCrossoverPoint, secondTableGeneticList.size()));

        List<TableGenetic> newFirstActionList = Stream.concat(part1FirstTableGeneticList.stream(), part2SecondTableGeneticList.stream())
                .collect(Collectors.toList());
        List<TableGenetic> newSecondActionList = Stream.concat(part1SecondTableGeneticList.stream(), part2FirstTableGeneticList.stream())
                .collect(Collectors.toList());

        Individual firstIndividual = new Individual(newFirstActionList);
        Individual secondIndividual = new Individual(newSecondActionList);

        return new IndividualPair(firstIndividual, secondIndividual);
    }
}
