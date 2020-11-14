

public class NeuralNetworkTest {

    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork("NN-0", 2, 1, 3, 10);
        double[] inputs = {10, 14};
        double[] outputs = nn.getOutputs(inputs);
        for (int i = 0; i < outputs.length; i++) {
            System.out.printf("Output %d = %f\n", i, outputs[i]);
        }
    }
}
