/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework_2;

/**
 *
 * @author Telwa
 */
public class StardandDeviation {

    private static  double Mean;
    private static double sqr;
    private static int size;
    private static Values features;

    public static Values Normalize(Values inputlist) {
        size = inputlist.getPixels().size();
        features = inputlist;

        findMean();

        findStandardDeviation();

        normalization();
        
        return features;
    }

    private static void findMean() {
        int sum = 0;
        for (Pixel input : features.getPixels()) {
            sum += input.getPixel();
        }
        double num = (sum/size);
        Mean = (num/ size);
    }

    private static  void findStandardDeviation() {
        double sum = 0;

        for (Pixel input : features.getPixels()) {
            sum += Math.pow(input.getPixel() - Mean, 2);
        }

        sqr = Math.sqrt(sum / size);

    }

    private static void normalization() {
        int i = 0;
        for (Pixel input : features.getPixels()) {
            double value = (input.getPixel() - Mean) / sqr;

            features.getPixels().set(i, new Pixel(value));
            i++;
        }
        
    }
}
