

public class NeuralNetworkTest {

    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork("NN-0",
                                             1,
                                             1,
                                             3,
                                             1);
        double[] inputs = {10, 12, 16, 20};
        double[] outputs = nn.getOutputs(inputs);
        for (int i = 0; i < outputs.length; i++) {
            System.out.printf("%s(%d) = %.3f\n", nn.getID(), i, outputs[i]);
        }
    }
}
