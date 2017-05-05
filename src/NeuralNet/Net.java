/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNet;

import coursework_2.Pixel;
import coursework_2.Values;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Telwa
 */
public class Net {

    private final List<Values> inputLayer;
    private List<Weights> weightLayer;
    private List<Pixel> hiddenLayer;
    private List<Weights> weightSecondLayer;
    private List<Pixel> outputLayer;
    private List<Pixel> errorRate;
    private List<Pixel> hiddenLayerError;

    private int length;
    private double bias = 0.35;
    private int pixellength;
    private int correctness;
    private double learningRate = 0.15;
    private int outputlayersize = 10;

    protected Net(List<Values> list) {
        this.inputLayer = list;
        this.weightLayer = new ArrayList<>();
        this.weightSecondLayer = new ArrayList<>();
        init();
    }

    protected Net(List<Values> list, List<Weights> l1_weights, List<Weights> l2_weights) {
       this.inputLayer = list;
       this.weightLayer = l1_weights;
       this.weightSecondLayer = l2_weights;
       init();
    }
    private void init()
    {
         this.errorRate = new ArrayList<>();
        this.hiddenLayer = new ArrayList<>();
        this.outputLayer = new ArrayList<>();
    }
    protected double setLearningRate(int errorrate, double weight) {
        return errorrate * getLearningRate() * weight;
    }

    protected void setInputLayer(Values value) {
        inputLayer.add(value);
    }

    protected void setWeightFirstLayer(Weights weight) {
        weightLayer.add(weight);
    }

    protected void setWeightSecondLayer(Weights weight) {
        weightSecondLayer.add(weight);
    }

    protected void setHiddenLayer(Pixel p) {
        hiddenLayer.add(p);
    }

    protected void resetHiddenLayer() {
        this.hiddenLayer = new ArrayList<>();
    }

    protected void resetOutputLayer() {
        this.outputLayer = new ArrayList<>();
    }

    protected void resetErrorRate() {
        this.errorRate = new ArrayList<>();
    }

    protected List<Pixel> getHiddenLayer() {
        return hiddenLayer;
    }

    protected void setOutputLayer(Pixel values) {
        outputLayer.add(values);
    }

    protected List<Pixel> getOutputLayer() {
        return outputLayer;
    }

    protected List<Weights> getWeightFirstLayer() {
        return weightLayer;
    }

    protected List<Weights> getWeightSecondLayer() {
        return weightSecondLayer;
    }

    protected List<Values> getInputLayer() {
        return inputLayer;
    }

    protected double adjustWeight(final double w, final double l , final double i, final double e) {
        double ie =  i * e;
    
        return (w - (l * ie)) ;
    }

    protected double sigmoid(final double e) {
        return( 1.0 / (1.0 + Math.exp(-e)));
    }

    protected double sigmoid_derivative(final double e) {
        return (e * (1 - e));
    }

    protected double computeHiddenLayer(final int I, final double w) {
        // System.out.println(" input "+I+" and weight"+w);
        return (I * w);
    }

    protected double computeOutputLayer(final double I, final double w) {
        return (I * w);
    }

    protected double computeError(final double target, final double output) {
        return -(target - output);

    }

    protected double calcuError(final double target, final double output) {
        return Math.sqrt(Math.pow((target - output), 2));
    }

    /**
     * @return the errorRate
     */
    public List<Pixel> getErrorRate() {
        return errorRate;
    }

    /**
     * @param errorRate the errorRate to set
     */
    public void setErrorRate(Pixel error) {
        errorRate.add(error);
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * @return the pixellength
     */
    public int getPixellength() {
        return pixellength;
    }

    /**
     * @param pixellength the pixellength to set
     */
    public void setPixellength(int pixellength) {
        this.pixellength = pixellength;
    }

    /**
     * @return the correctness
     */
    public int getCorrectness() {
        return correctness;
    }

    /**
     * @param correctness the correctness to set
     */
    public void setCorrectness(int correctness) {
        this.correctness = correctness;
    }

    /**
     * @return the learningRate
     */
    public double getLearningRate() {
        return learningRate;
    }

    /**
     * @param learningRate the learningRate to set
     */
    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    /**
     * @return the outputlayersize
     */
    public int getOutputlayersize() {
        return outputlayersize;
    }

    /**
     * @param outputlayersize the outputlayersize to set
     */
    public void setOutputlayersize(int outputlayersize) {
        this.outputlayersize = outputlayersize;
    }

    /**
     * @return the bias
     */
    public double getBias() {
        return bias;
    }

    /**
     * @return the hiddenLayerError
     */
    public List<Pixel> getHiddenLayerError() {
        return hiddenLayerError;
    }

    /**
     * @param hiddenLayerError the hiddenLayerError to set
     */
    public void setHiddenLayerError(List<Pixel> hiddenLayerError) {
        this.hiddenLayerError = hiddenLayerError;
    }
}
