/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework_2;

import NeuralNet.Weights;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Telwa
 */
public class ReadFile {

    private List<Values> list = new ArrayList<>();
    private static final String WEIGHT_FILE_PATH = "src/weightss/trained";
    private static final String WEIGHT_FILE_PATH2 = "src/weightss/trained2";
    private List<Weights> weightList = new ArrayList<>();

    @SuppressWarnings("null")
    public List<Values> Readfile() throws IOException {

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        
        File f = new File(sc.nextLine());
        BufferedReader reader = new BufferedReader(new FileReader(f));

        String ln;

        while ((ln = reader.readLine()) != null) {

            String[] line = ln.split(",");

            readValues(line);

        }
        reader.close();
       System.out.println("..... Read file........");
        return list;
    }

    public List<Weights> ReadWeights() throws FileNotFoundException, IOException {
        File f = new File(WEIGHT_FILE_PATH);
        BufferedReader reader = new BufferedReader(new FileReader(f.getAbsoluteFile()));

        String ln;

        while ((ln = reader.readLine()) != null) {

            String[] line = ln.split(",");
            int length = line.length;

            for (int i = 0; i < length; i++) {
                Weights w = new Weights();
                w.setWeight(Double.parseDouble(line[i]));
                weightList.add(w);
            }

        }
        return weightList;
    }
    public List<Weights> ReadWeights2() throws FileNotFoundException, IOException {
        File f = new File(WEIGHT_FILE_PATH2);
        BufferedReader reader = new BufferedReader(new FileReader(f.getAbsoluteFile()));

        String ln;

        while ((ln = reader.readLine()) != null) {

            String[] line = ln.split(",");
            int length = line.length;

            for (int i = 0; i < length; i++) {
                Weights w = new Weights();
                w.setWeight(Double.parseDouble(line[i]));
                weightList.add(w);
            }

        }
        return weightList;
    }
    

    private void readValues(String[] args) {
        // Temporally list of values

        //System.out.println("... " + args.length);
        // loop thought the string array
        // Contains the feature of the label
        // variable to hold list of features temporary
        List<Pixel> temp = new ArrayList<>();
        //temp.add(new Pixel(1));
        int length = args.length;
        for (int i = 0; i < length; i++) {

            int label = 0;

            if (i == args.length - 1) {// checks for the last value in the list

                // variable type Values holds features and label
                Values newValue = null;
                label = Integer.parseInt(args[i]);
                //System.out.println(label);
                // put the label or the image number if you must.
                newValue = new Values(label);
                // sets the features in the pixel class
                newValue.setPixel(temp);

                list.add(newValue);
                continue;
            } else {
                // sets each feature in the temp list
                Pixel p = new Pixel(Integer.parseInt(args[i]));
                temp.add(p);

            }

        }

    }

    public void WriteWeights(List<Weights> weights,int file) throws IOException
    {
        File f; 
        if(1 == 1){
         f = new File(WEIGHT_FILE_PATH);
        }
        else if(2 == file)
        {
            f = new File(WEIGHT_FILE_PATH2);
        }
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(f.getAbsoluteFile()));
        
        for(Weights w : weights)
        {
            int i =0;
            for(double wd : w.getWeights())
            {
                
                if(i == w.getWeights().size() - 1)
                {
                    bw.write(""+wd+"");
                }
                bw.write(wd+",");
                i++;
            }
            bw.newLine();
            
        }
        
        bw.close();
        
        
    }
}
