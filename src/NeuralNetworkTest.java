

public class NeuralNetworkTest {

    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork("NN-0",
                                             4,
                                             6,
                                             3,
                                             5);
        double[] inputs = {5, 15, 1, 1};
        double[] outputs = nn.getOutputs(inputs);
        for (int i = 0; i < outputs.length; i++) {
            System.out.printf("%s(%d) = %.3f\n", nn.getID(), i, outputs[i]);
        }
    }
}
