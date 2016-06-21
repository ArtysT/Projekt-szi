package LogicalLayer.genetic;

public class IndividualPair {
    private Individual firstIndividual;
    private Individual secondIndividual;

    public IndividualPair(Individual firstIndividual, Individual secondIndividual) {
        this.firstIndividual = firstIndividual;
        this.secondIndividual = secondIndividual;
    }

    public Individual getFirst() {
        return firstIndividual;
    }

    public Individual getSecond() {
        return secondIndividual;
    }
}
