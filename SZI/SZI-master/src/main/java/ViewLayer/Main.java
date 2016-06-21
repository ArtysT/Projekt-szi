package ViewLayer;

import LogicalLayer.decisionTree.TreeTest;
import LogicalLayer.genetic.GeneticAlgorithm;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        geneticAlgorithm.findTheBestIndividual(100000);
        MainFrame okno = MainFrame.getInstance();


    }

}

