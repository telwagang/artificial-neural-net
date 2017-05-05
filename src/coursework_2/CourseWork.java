/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework_2;

import KNN.Nearest_Neighbor;
import NeuralNet.ANN;
import java.io.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Telwa
 */
public class CourseWork {

    /**
     * @param args the command line arguments
     */
    private static List<Values> list;

    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        //returns the input layer for the 
        //text file 
        

        System.out.println("select by number:.........");
        System.out.println("");
        System.out.println("");
        System.out.println("1. Artificial Nerual Net");
        System.out.println("2. nearest neighbor");
        System.out.println("");

        Scanner sc = new Scanner(System.in);
        String c = sc.nextLine();
        if (1 == Integer.parseInt(c)) {

            System.out.println(" ");
            System.out.println("1. Train");
            System.out.println("2. Run ");
            System.out.println("");
            String v = sc.nextLine();
            if (1 == Integer.parseInt(v)) {
                System.out.println("Enter the training set path of the file....: ");
                list = new ReadFile().Readfile();
                ANN nn = new ANN(list);
                nn.train();
            } else if (2 == Integer.parseInt(v)) {
                System.out.println("Enter the test set path of the file....: ");
                 list = new ReadFile().Readfile();
                 
                ANN nn = new ANN( new ReadFile().ReadWeights(),new ReadFile().ReadWeights2(),list);
                nn.run();
            }
        } else if (2 == Integer.parseInt(c)) {
            System.out.println("Enter the training set path of the file....: ");
            list = new ReadFile().Readfile();
            System.out.println("Enter the test  set path of the file....: ");
            Nearest_Neighbor knn = new Nearest_Neighbor(list, new ReadFile().Readfile());
            knn.train();
        }
        else{
         System.out.println("Please select again... ");
        }
        //We pass the input values as an argument to 
        //Neural net class 
        //ANN nn = new ANN(list);
        //nn.train();

       

    }
    

}
