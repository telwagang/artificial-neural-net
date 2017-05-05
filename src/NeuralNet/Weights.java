package NeuralNet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Telwa
 */
public class Weights {

    private List<Double> weightList;
    private int length;

    public Weights(int size) {
        // the size the a weight should generate
        this.length = size;
        weightList = new ArrayList<>();
        generateWeights();

    }

    public Weights() {
        weightList = new ArrayList<>();
    }

    public void setWeight(double value) {
        weightList.add(value);
    }

    public List<Double> getWeights() {
        return weightList;
    }

    private void generateWeights() {
        // generates and sets weights
        // creating two weights for each input
        for (int i = 0; i < length; i++) {
            // random method creates a random weight for 0.0 to 0.9
            // round method rounds off the values
            // sets the values to neural net weight list
            double a = round();
            setWeight(a);
            //System.out.println(a);
        }
    }

    private double random() {
        // random number generator from java 7 API
        return ThreadLocalRandom.current().nextDouble(0.0, 0.9);
    }

    public static double round(double x) {
        // round off to 1 digit decimal place.
        return (Math.round(x * 10.0) / 10.0);
    }

    private double round() {
        // override of round method
        return round(random());
    }

}
