package NeuralNet;

import coursework_2.Pixel;
import coursework_2.ReadFile;
import coursework_2.StardandDeviation;
import coursework_2.Values;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Telwa
 */
public class ANN extends Net {

    public ANN(List<Values> list) {
        super(list);
        // check the size of the inputlayer values
        // so to create the weights for each value length
        setLength(getInputLayer().size());
        setPixellength(getInputLayer().get(0).getPixels().size() + 6);
        setCorrectness(0);
        makeWeights();
    }

    public ANN(List<Weights> l1_weight, List<Weights> l2_weight, List<Values> list) {
        super(list, l1_weight, l2_weight);
        setCorrectness(0);

    }

    public void run() {
        for (Values inputlist : getInputLayer()) {
            Values input = StardandDeviation.Normalize(inputlist);
            feedForward(input);

            markResults(input);
        }
        printAccuracy();
    }

    public void train() {
        // /Users/pro/Desktop/uni docs/csd 3939/cw2DataSet1.csv
        // /Users/pro/Desktop/uni docs/csd 3939/datatest.csv
        // /Users/pro/Desktop/uni docs/csd 3939/datatest_2.csv

        // loop through each input node value
        for (Values inputlist : getInputLayer()) {
            // Summation reset
            // store the summation of each input value and
            // weight
            // 0 0 0.1 => 0
            // 1 1 0.6 => 0
            // 2 2 0.5 => 0
            Values input = StardandDeviation.Normalize(inputlist);
            recrusiveTrain(input);

            System.out.println(" ");
            System.out.println(" ........ new file ........");

        }
        try {
            new ReadFile().WriteWeights(getWeightFirstLayer(),1);
        } catch (IOException ex) {
            Logger.getLogger(ANN.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            new ReadFile().WriteWeights(getWeightSecondLayer(), 2);
        } catch (IOException ex) {
            Logger.getLogger(ANN.class.getName()).log(Level.SEVERE, null, ex);
        }
        run();
    }

    private void recrusiveTrain(Values inputlist) {
            double err = 0.0;
        for (int i = 0; i < 10000; i++) {

            double error = feedForward(inputlist);
            backPropagation(inputlist);

            resetHiddenLayer();
            resetOutputLayer();
            resetErrorRate();

            if (error < 0.9) {

                //recrusiveTrain(inputlist);
                break;
            }
            err = error;
        }
        if(err> 1.0)
        {
            recrusiveTrain(inputlist);
        }
    }

    private void makeWeights() {

        for (int i = 0; i < getPixellength(); i++) {
            if (i < getInputLayer().get(0).getPixels().size()) {
                setWeightFirstLayer(new Weights(getPixellength()));
            }
            setWeightSecondLayer(new Weights(getOutputlayersize()));

        }

    }

    private double feedForward(Values inputlist) {
        forHidden(inputlist);
        return forOutput(inputlist.getImageNumber());
    }

    private void forHidden(Values inputlist) {
        for (int j = 0; j < getPixellength(); j++) {
            double summation = 0.0;

            ListIterator<Pixel> inputIterate
                    = inputlist.getPixels().listIterator();

            //compute the hidden layer by summation of the weights and the input node.
            // hidden node = wiegth * input node 
            while (inputIterate.hasNext()) {
                int c = inputIterate.nextIndex();
                // add each
                // ai = sum( w * i +.... )
                summation += computeHiddenLayer(
                        inputIterate.next().getPixel(),
                        getWeightFirstLayer()
                                .get(c)
                                .getWeights()
                                .get(j));
            }

            // apply activation function => sigmoid function 
            double sighiddenvalue = sigmoid(summation);
            // set the hidden node 
            setHiddenLayer(new Pixel(sighiddenvalue));
        }
    }

    private double forOutput(int image) {
        // compute the output layer 
        // ouput = weights * hidden nodes 
        // apply activation
        // find the delta 
        double score = 0.0;

        for (int j = 0; j < getOutputlayersize(); j++) {
            double summation = 0.0;
            ListIterator<Pixel> hiddenlayer = getHiddenLayer()
                    .listIterator();

            while (hiddenlayer.hasNext()) {
                int c = hiddenlayer.nextIndex();
                // add each
                // aj = sum( w * i +.... )
                summation += computeOutputLayer(
                        hiddenlayer.next().getHidden(),
                        getWeightSecondLayer()
                                .get(c)
                                .getWeights()
                                .get(j));

            }
            //apply activation function => sigmoid function 
            double sigoutputvalue = sigmoid(summation);
            //add values to  the output layer 
            setOutputLayer(new Pixel(sigoutputvalue));

            score = deltaOutput(j, image, sigoutputvalue, score);

        }

        System.out.println(" ");
        System.out.println(" the predicted error rate  :" + score);

        return score;

    }

    private double deltaOutput(int j, int getImage, double sigoutputvalue, double score) {

        //# 3         //how much did it miss 
        double signal_error = 0.0;
        double error = 0.0;
        if (j == getImage) {
            signal_error = computeError(1.0, sigoutputvalue);
            error = calcuError(1.0, sigoutputvalue);

        } else {
            signal_error = computeError(0.0, sigoutputvalue);
            error = calcuError(0.0, sigoutputvalue);
        }
        //am looking for a sum of zero
        // this will prove the outputs are correct 
        score += error;

        double delta = signal_error * sigmoid_derivative(sigoutputvalue);
        //store the delta 
        setErrorRate(new Pixel(delta));
        return score;
    }

    private void backPropagation(Values inputlist) {

        //# 4   hidden layer back propagation
        List<Double> l1_delta = new ArrayList<>();
        for (int i = 0; i < getPixellength(); i++) {
            double sum = 0.0;
            for (int j = 0; j < getOutputlayersize(); j++) {
                for (int k = 0; k < getPixellength(); k++) {
                    //sum += output delta * output weights
                    sum += computeOutputLayer(getErrorRate()
                            .get(j)
                            .getHidden(),
                            getWeightSecondLayer()
                                    .get(k)
                                    .getWeights()
                                    .get(j));
                }
            }
            // delta = sum (e * (1 - e));
            double delta =  sum * sigmoid_derivative(getHiddenLayer()
                    .get(i).getHidden()) ;
            l1_delta.add(delta);
        }

        // # 5    adjusting weights in hidden output weights 
        for (int j = 0; j < getHiddenLayer().size(); j++) {
            Weights l2_weights = null;
            for (int k = 0; k < getOutputlayersize(); k++) {
                for (int i = 0; i < getPixellength(); i++) {
                    l2_weights = getWeightSecondLayer().get(i);

                    // compute weight changes 
                    // weight = old weight + learming rate x input x errorrate 
                    double error2 = adjustWeight(l2_weights.getWeights().get(k),
                            getLearningRate(),
                            getOutputLayer()
                                    .get(k)
                                    .getHidden(),
                            getErrorRate()
                                    .get(k)
                                    .getHidden());

                    //set the new weights 
                    l2_weights.getWeights().set(k, error2);
                }
            }
            getWeightSecondLayer().set(j, l2_weights);
        }

        // # 6   adjust weights from input to the hidden layer
        for (int i = 0; i < inputlist.getPixels().size(); i++) {
            Weights l1_weight = null;
            for (int j = 0; j < l1_delta.size(); j++) {
                for (int k = 0; k < getPixellength() - 6; k++) {

                    l1_weight = getWeightFirstLayer().get(k);

                    // weight = old weight + learming rate x input x errorrate 
                    double weight_error = adjustWeight(l1_weight.getWeights()
                            .get(j),
                            getLearningRate(),
                            inputlist.getPixels()
                                    .get(k)
                                    .getPixel(),
                            l1_delta
                                    .get(j));

                    //set the new weights 
                    l1_weight.getWeights().set(j, weight_error);

                }
            }
            getWeightFirstLayer().set(i, l1_weight);
        }

    }

    private void markResults(Values inputlist) {
        int i = 0;
        for (Pixel output : getOutputLayer()) {
            if (output.getHidden() == 1.0) {
                if (i == inputlist.getImageNumber()) {
                    setCorrectness(getCorrectness() + 1);
                }
                break;
            }
            i++;
        }
    }

    private void printAccuracy() {

        System.out.println(" ");
        float percent = ((getCorrectness() * 100)/ getLength());
        System.out.println("correctness....:" + percent + "%");
    }
    
    private List<Values> nFold(List<Values> input)
    {
        int size = input.size();
        int folds = 3;
        int seed = 4;
        //Random rand = new Random(seed);
        // randdata = new Instances();
        // Instances sd;
        return input;
    }
}
