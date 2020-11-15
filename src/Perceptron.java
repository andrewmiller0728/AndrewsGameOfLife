import java.util.Arrays;
import java.util.Random;

public class Perceptron {

    private String ID;
    private double[] weights;
    private double bias;

    public Perceptron(String ID, int inputCount) {
        this.ID = ID;
        this.weights = new double[inputCount];
        for (int i = 0; i < inputCount; i++) {
            Random r = new Random();
            weights[i] = r.nextDouble();
        }
        this.bias = 0;
    }

    public Perceptron(String id, double[] weights, double bias) {
        this.ID = id;
        this.weights = Arrays.copyOf(weights, weights.length);
        this.bias = bias;
    }

    public double getOutput(double input) {
        return ReLU((input * weights[0]) + bias);
    }

    public double getOutput(double[] inputs) {
        double output = 0;
        for (int i = 0; i < inputs.length; i++) {
            output += inputs[i] * weights[i];
        }
        return ReLU(output + bias);
    }

    private double ReLU(double x) {
        if (x > 0) {
            return x;
        }
        else {
            return 0;
        }
    }

    public Perceptron getCopy() {
        return new Perceptron(this.ID, this.weights, this.bias);
    }


    /*
    Getters and Setters
     */

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }
}
