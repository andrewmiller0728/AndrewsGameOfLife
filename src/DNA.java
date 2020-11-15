import java.util.ArrayList;
import java.util.Random;

public class DNA {

    private NeuralNetwork neuralNetwork;

    public DNA(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork.getCopy();
    }

    public void mutate(double factor) {
        ArrayList<ArrayList<Perceptron>> layers = neuralNetwork.getLayers();
        for (ArrayList<Perceptron> currLayer : layers) {
            for (Perceptron currNode : currLayer) {
                double[] currWeights = currNode.getWeights();
                double currBias = currNode.getBias();
                for (int i = 0; i < currWeights.length; i++) {
                    currWeights[i] = this.tweak(currWeights[i], factor);
                    currBias = this.tweak(currBias, factor);
                }
                currNode.setWeights(currWeights);
                currNode.setBias(currBias);
            }
        }
    }

    public NeuralNetwork getNeuralNetwork() {
        return neuralNetwork;
    }

    private double tweak(double value, double factor) {
        Random r = new Random();
        if (r.nextDouble() >= 0.5) {
            value += value * factor;
        }
        else {
            value -= value * factor;
        }
        return value;
    }
}
