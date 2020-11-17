

public class Cell {

    private static final int INIT_ENERGY = 50;
    private static final double OUTPUT_THRESHOLD = 0.0001;

    private String ID;
    private int age;
    private NeuralNetwork brain;
    private Vector2D location;
    private int energy;
    private Plane2D plane;


    public Cell(String id, Plane2D plane, Vector2D location) {
        this.ID = id;
        this.age = 0;

        // NN inputs: location, energy, Moore Neighborhood
        // NN outputs: moveN, moveE, moveS, moveW, eat, divide
        this.brain = new NeuralNetwork("NN".concat(ID), 11, 7, 2, 16);

        this.location = location;
        this.energy = INIT_ENERGY;
        this.plane = plane;
    }

    private double[] getMooreNeighborhood() {
        Vector2D[] neighborhood = new Vector2D[8];
        neighborhood[0] = new Vector2D(location.getX(),     location.getY() + 1);
        neighborhood[1] = new Vector2D(location.getX() + 1, location.getY() + 1);
        neighborhood[2] = new Vector2D(location.getX() + 1, location.getY());
        neighborhood[3] = new Vector2D(location.getX() + 1, location.getY() - 1);
        neighborhood[4] = new Vector2D(location.getX(),     location.getY() - 1);
        neighborhood[5] = new Vector2D(location.getX() - 1, location.getY() - 1);
        neighborhood[6] = new Vector2D(location.getX() - 1, location.getY());
        neighborhood[7] = new Vector2D(location.getX() - 1, location.getY() + 1);

        double[] neighbors = new double[neighborhood.length];
        for (int i = 0; i < neighborhood.length; i++) {
            if (plane.checkPoint(neighborhood[i]) == EntityType.CELL) {
                neighbors[i] = 2;
            }
            else if (plane.checkPoint(neighborhood[i]) == EntityType.ENERGY) {
                neighbors[i] = 1;
            }
            else {
                neighbors[i] = 0;
            }
        }
        return neighbors;
    }

    private ActionType getAction(double[] inputs) {
        int choice = getIndexGreatest(brain.getOutputs(inputs));
        return switch (choice) {
            case 0 -> ActionType.MOVE_NORTH;
            case 1 -> ActionType.MOVE_EAST;
            case 2 -> ActionType.MOVE_SOUTH;
            case 3 -> ActionType.MOVE_WEST;
            case 4 -> ActionType.EAT;
            case 5 -> ActionType.DIVIDE;
            default -> ActionType.SLEEP;
        };
    }

    private static int getIndexGreatest(double[] choices) {
        double greatest = OUTPUT_THRESHOLD;
        int index = -1;
        for (int i = 0; i < choices.length; i++) {
            if (choices[i] > greatest) {
                greatest = choices[i];
                index = i;
            }
        }
        return index;
    }
}
