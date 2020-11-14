import java.util.ArrayList;
import java.util.Arrays;

public class NeuralNetwork {

    private String ID;
    private ArrayList<Perceptron> inputNodes, outputNodes;
    private ArrayList<ArrayList<Perceptron>> layers;

    public NeuralNetwork(String id, int inputCount, int outputCount,
                         int hiddenLayerCount, int hiddenLayerSize) {
        this.ID = id;

        layers = new ArrayList<>();

        inputNodes = new ArrayList<>();
        for (int i = 0; i < inputCount; i++) {
            String currID = String.format("PI-%d", i);
            inputNodes.add(new Perceptron(currID, 1));
        }
        layers.add(inputNodes);

        for (int i = 0; i < hiddenLayerCount; i++) {
            ArrayList<Perceptron> layer = new ArrayList<>();
            for (int j = 0; j < hiddenLayerSize; j++) {
                String currID = String.format("PH%d-%d", i, j);
                layer.add(new Perceptron(currID, layers.get(i).size()));
            }
            layers.add(layer);
        }

        outputNodes = new ArrayList<>();
        for (int i = 0; i < outputCount; i++) {
            String currID = String.format("PO-%d", i);
            outputNodes.add(new Perceptron(currID, layers.get(layers.size() - 1).size()));
        }
        layers.add(outputNodes);
    }

    public double[] getOutputs(double[] inputs) {
        System.out.println("Getting output from neural network...");
        double[] layerOutputs = null;
        double[] prevLayerOutputs = null;
        for (ArrayList<Perceptron> currLayer : layers) {
            System.out.printf("Working on layer %d, size = %d\n", layers.indexOf(currLayer), currLayer.size());
            layerOutputs = new double[currLayer.size()];
            for (int j = 0; j < currLayer.size(); j++) {
                if (prevLayerOutputs == null) {
                    layerOutputs[j] = currLayer.get(j).getOutput(inputs[j]);
                } else {
                    layerOutputs[j] = currLayer.get(j).getOutput(prevLayerOutputs[j]);
                }
                prevLayerOutputs = Arrays.copyOf(layerOutputs, layerOutputs.length);
            }
        }
        return Arrays.copyOf(layerOutputs, layerOutputs.length);
    }
}
