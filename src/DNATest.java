import java.util.Arrays;

public class DNATest {

    public static void main(String[] args) {

        double[] inputs = {1, 4};

        NeuralNetwork nn = new NeuralNetwork("NN-0",
                                        1,
                                       2,
                                   3,
                                    4);
        System.out.printf("Output 1:\n\t%s({1, 4}) = %s\n",
                nn.getID(),
                Arrays.toString(nn.getOutputs(inputs)));
        DNA dna = new DNA(nn);
        dna.mutate(0.1);

        NeuralNetwork nn2 = dna.getNeuralNetwork();
        System.out.printf("Output 2:\n\t%s({1, 4}) = %s\n",
                nn2.getID(),
                Arrays.toString(nn2.getOutputs(inputs)));
        DNA dna2 = new DNA(nn);
        dna2.mutate(0.1);

        NeuralNetwork nn3 = dna.getNeuralNetwork();
        System.out.printf("Output 2:\n\t%s({1, 4}) = %s\n",
                nn3.getID(),
                Arrays.toString(nn3.getOutputs(inputs)));
        DNA dna3 = new DNA(nn);
        dna3.mutate(0.1);
    }

}
