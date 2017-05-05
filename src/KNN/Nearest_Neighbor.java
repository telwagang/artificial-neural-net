/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KNN;

import coursework_2.Pixel;
import coursework_2.StardandDeviation;
import coursework_2.Values;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Telwa
 */
public class Nearest_Neighbor {

    private final List<Values> inputList;
    private final List<Values> inputListCopy;
    private List<kpixel> resultsList;
    private int NFold = 3;
    private int correctness =0 ; 
    private List<Values> trainningList;
    private List<Values> testList;

    public Nearest_Neighbor(List<Values> list,List<Values> tlist) {
        this.inputList = list;
        this.testList = tlist;
        this.inputListCopy = new ArrayList<>();

    }

    public void work() {

//        int legnth = inputList.size();
//        
//        for(int i = 0; i<length; )
    }

    public void train() {
        // /Users/pro/Desktop/uni docs/csd 3939/cw2DataSet1.csv
        // set /Users/pro/Desktop/uni docs/csd 3939/datatest.csv
        // test /Users/pro/Desktop/uni docs/csd 3939/datatest_2.csv
        //files 
        for (Values testvalue : testList) {
            //to store each result
            resultsList = new ArrayList<>();
            //each feature in the dataset
           
            
            for (Values storedvalues : inputList) {
                double sum = 0.0;
               //Euclidean distance computation
                ListIterator<Pixel> pixel = storedvalues.getPixels().listIterator();
                while(pixel.hasNext())
                {
                    //sum = ( x - y )ˆ2 + ....nx 
                    
                    sum += calculateDistance.half(testvalue.getPixels()
                            .get(pixel.nextIndex()).getPixel(),
                            pixel.next().getPixel());
                }
                //store the labe and output 
                kpixel kp = new kpixel(storedvalues.getImageNumber());
                //find the sqr ∑sum 
                kp.setDistance(calculateDistance.distance(sum));
                //add to list 
                resultsList.add(kp);
            }
            //check if for the predicted label
            int size = resultsList.size();
            
            double indexvalue = resultsList.get(0).getDistance();
            
            int label = resultsList.get(0).getPixel();
            
            for(int i=0; i<size; i++)
            {
                //find the lowerest value 
                 double v = resultsList.get(i).getDistance();
                 
                 if (indexvalue > v) {
                     //set the smallest value 
                     indexvalue = resultsList.get(i).getDistance();
                    label = resultsList.get(i).getPixel();
                    
                    }
            }
            
            System.out.println(" ");
            System.out.println("   predicted image...:"+label+" actual image..."+testvalue.getImageNumber());
             System.out.println(" ");
             //
            if(testvalue.getImageNumber() == label)
            {
                correctness++;
            } else {
                 
            }
          
            
            
        }
          System.out.println(" ");
        float percent = ((correctness * 100 ) /testList.size());
        System.out.println(percent+"%");
        
    }

}
