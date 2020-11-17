import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GeneticLineClassifier {

    private static final int INIT_SIZE = 100;
    private static final double LEARNING_RATE = 0.01;
    private static final int ITERATIONS = (int) Math.pow(10, 6);
    private static final double THRESHOLD = 0.0001;

    public static void main(String[] args) {
        NeuralNetwork nn;
        DNA startingDNA = null;
        ArrayList<NeuralNetwork> population = new ArrayList<>();
        for (int i = 0; i < INIT_SIZE; i++) {
            nn = new NeuralNetwork("NN-0",
                                   2,
                                   2,
                                   3,
                                   5);
            startingDNA = new DNA(nn);
            population.addAll(createOffspring(startingDNA, 1));
        }

        double[] inputs1 = {81, 6};
        double[] inputs2 = {2, 90};
        double[] inputs3 = {35, 50};
        double[] inputs4 = {3, 1};

        System.out.println("Before Training:\n");
        System.out.println(getPopulationSurvey(population, inputs1));
        System.out.println(getPopulationSurvey(population, inputs2));
        System.out.println(getPopulationSurvey(population, inputs3));
        System.out.println(getPopulationSurvey(population, inputs4));


        System.out.print("Training ");
        for (int i = 1; i <= ITERATIONS; i++) {
            Random rand = new Random();

            double[] currInput = {rand.nextDouble() * 100, rand.nextDouble() * 100};
            int choice = 1;
            if (currInput[1] > f(currInput[0])) {
                choice = 0;
            }
            population = getFittest(population, currInput, choice);

            if (population.size() == 0) {
                population.addAll(createOffspring(startingDNA, INIT_SIZE));
            }
            else if (INIT_SIZE - population.size() > 0) {
                DNA newParentDNA = new DNA(population.get(rand.nextInt(population.size())));
                population.addAll(createOffspring(newParentDNA, INIT_SIZE - population.size()));
            }

            if (i % (ITERATIONS / 10) == 0) {
                System.out.printf("...%d ", 10 * i / ITERATIONS);
            }
        }
        System.out.println();

        System.out.println("After Training:\n");
        System.out.println(getPopulationSurvey(population, inputs1));
        System.out.println(getPopulationSurvey(population, inputs2));
        System.out.println(getPopulationSurvey(population, inputs3));
        System.out.println(getPopulationSurvey(population, inputs4));
    }

    private static double f(double x) {
        return Math.pow(x, 2);
    }

    private static ArrayList<NeuralNetwork> createOffspring(DNA parentDNA, int count) {
        ArrayList<NeuralNetwork> offspring = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            parentDNA.mutate(LEARNING_RATE);
            offspring.add(parentDNA.getNeuralNetwork().getCopy());
        }
        return offspring;
    }

    private static ArrayList<NeuralNetwork> getFittest(ArrayList<NeuralNetwork> population, double[] inputs, int outputChoice) {
        ArrayList<NeuralNetwork> fittest = new ArrayList<>();
        for (NeuralNetwork individual : population) {
            double[] currOutputs = individual.getOutputs(inputs);
            double choice = getIndexGreatest(currOutputs);
            if (choice == outputChoice) {
                fittest.add(individual.getCopy());
            }
        }
        return fittest;
    }

    private static double getIndexGreatest(double[] choices) {
        double greatest = THRESHOLD;
        double index = -1;
        for (int i = 0; i < choices.length; i++) {
            if (choices[i] > greatest) {
                greatest = choices[i];
                index = i;
            }
        }
        return index;
    }

    private static String getPopulationSurvey(ArrayList<NeuralNetwork> population, double[] inputs) {
        String str = "Population Survey:\ninputs = " + Arrays.toString(inputs) + "\n";
        int output0Count = 0;
        int output1Count = 0;
        for (NeuralNetwork neuralNetwork : population) {
            double[] currOutputs = neuralNetwork.getOutputs(inputs);
            if (currOutputs[1] > currOutputs[0]) {
                output0Count++;
            } else {
                output1Count++;
            }
        }
        str = str.concat(String.format("\tOutput 0: %d / %d\n", output0Count, population.size()));
        str = str.concat(String.format("\tOutput 1: %d / %d\n", output1Count, population.size()));
        return str;
    }

    private static String getPopulationOutputs(ArrayList<NeuralNetwork> population, double[] inputs) {
        String str = "Population(" + Arrays.toString(inputs) + "):";
        for (int i = 0; i < population.size(); i++) {
            str = str.concat(String.format("\tIndividual %d:\n", i));
            double[] currOutputs = population.get(i).getOutputs(inputs);
            for (int j = 0; j < currOutputs.length; j++) {
                str = str.concat(String.format("\t\tOutput[%d] = %.4f\n", j, currOutputs[j]));
            }
        }
        return str;
    }
}
