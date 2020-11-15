import java.util.ArrayList;
import java.util.Random;

public class GeneticLineClassifier {

    public static void main(String[] args) {
        int initSize = 10;
        NeuralNetwork nn = new NeuralNetwork("NN-0",
                                             2,
                                             2,
                                             3,
                                             5);
        DNA startingDNA = new DNA(nn);
        ArrayList<NeuralNetwork> population = createOffspring(startingDNA, initSize);
        double[] inputs = {2, 4};

        System.out.println("Before Training:");
        System.out.println(getPopulationOutputs(population, inputs));

        int iterations = 10;
        for (int i = 0; i < iterations; i++) {
            Random rand = new Random();
            double[] currInput = {rand.nextDouble() * 100, rand.nextDouble() * 100};
            int choice = 1;
            if (currInput[1] > (currInput[0])) {
                choice = 0;
            }
            population = getFittest(population, currInput, choice);
            if (population.size() < initSize) {
                DNA newParentDNA = new DNA(population.get(rand.nextInt(population.size())));
                population.addAll(createOffspring(newParentDNA, initSize - population.size()));
            }
        }

        System.out.println("After Training:");
        System.out.println(getPopulationOutputs(population, inputs));
    }

    private static double f(double x) {
        return x;
    }

    private static ArrayList<NeuralNetwork> createOffspring(DNA parentDNA, int count) {
        ArrayList<NeuralNetwork> offspring = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            parentDNA.mutate(0.1);
            offspring.add(parentDNA.getNeuralNetwork().getCopy());
        }
        return offspring;
    }

    private static ArrayList<NeuralNetwork> getFittest(ArrayList<NeuralNetwork> population, double[] inputs, int outputChoice) {
        ArrayList<NeuralNetwork> fittest = new ArrayList<>();
        for (NeuralNetwork individual : population) {
            double[] currOutputs = individual.getOutputs(inputs);
            double choice = getIndexGreatest(currOutputs, 0);
            if (choice == outputChoice) {
                fittest.add(individual.getCopy());
            }
        }
        return fittest;
    }

    private static double getIndexGreatest(double[] choices, double threshold) {
        double greatest = threshold;
        double index = -1;
        for (int i = 0; i < choices.length; i++) {
            if (choices[i] > greatest) {
                greatest = choices[i];
                index = i;
            }
        }
        return index;
    }

    private static String getPopulationOutputs(ArrayList<NeuralNetwork> population, double[] inputs) {
        String str = "Population:\n";
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
