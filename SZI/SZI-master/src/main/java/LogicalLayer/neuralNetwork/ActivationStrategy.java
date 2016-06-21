package LogicalLayer.neuralNetwork;


public interface ActivationStrategy {
    double activate(double weightedSum);
    double derivative(double weightedSum);
    ActivationStrategy copy();
}
