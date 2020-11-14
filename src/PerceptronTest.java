import java.util.ArrayList;

public class PerceptronTest {

    public static void main(String[] args) {
        ArrayList<Perceptron> input = new ArrayList<>();
        ArrayList<Perceptron> hidden = new ArrayList<>();
        ArrayList<Perceptron> output = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            input.add(new Perceptron(String.format("PI-%s", Integer.toString(i))));

            hidden.add(new Perceptron(String.format("PH-%s", Integer.toString(i))));

            output.add(new Perceptron(String.format("PO-%s", Integer.toString(i))));
        }
    }

//    private static void test1() {
//        Perceptron p1 = new Perceptron("0");
//        double[] inputs = {10, 10};
//        double[] weights = {0.5, 0.5};
//        double bias = 3;
//
//        p1.setInputs(inputs);
//        p1.setWeights(weights);
//        p1.setBias(bias);
//
//        System.out.printf("P%s -> %f\n", p1.getID(), p1.getOutput());
//    }
}
