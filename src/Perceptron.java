
public class Perceptron {

    private String ID;
    private double[] inputs;
    private double[] weights;
    private double bias;

    public Perceptron(String ID) {
        this.ID = ID;
        this.inputs = null;
        this.weights = null;
        this.bias = 0;
    }

    public Perceptron(String ID, double[] inputs, double[] weights, double bias) {
        this.ID = ID;
        this.inputs = inputs;
        this.weights = weights;
        this.bias = bias;
    }

    public double getOutput() {
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


    /*
    Getters and Setters
     */

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double[] getInputs() {
        return inputs;
    }

    public void setInputs(double[] inputs) {
        this.inputs = inputs;
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
