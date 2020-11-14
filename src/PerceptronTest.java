import java.util.Arrays;

public class PerceptronTest {

    public static void main(String[] args) {
        Perceptron input = new Perceptron("PI-0", 1);
        System.out.printf("Input Weights: %s\n", Arrays.toString(input.getWeights()));

        Perceptron hidden = new Perceptron("PH-0", 1);
        System.out.printf("Hidden Weights: %s\n", Arrays.toString(hidden.getWeights()));

        Perceptron output = new Perceptron("PO-0", 1);
        System.out.printf("Output Weights: %s\n", Arrays.toString(output.getWeights()));

        double x = 10;

        double inputOutput = input.getOutput(x);
        System.out.printf("input(%f) = %f\n", x, inputOutput);

        double hiddenOutput = input.getOutput(inputOutput);
        System.out.printf("hidden(%f) = %f\n", inputOutput, hiddenOutput);

        double outputOutput = input.getOutput(hiddenOutput);
        System.out.printf("output(%f) = %f\n", hiddenOutput, outputOutput);
    }
}
