/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework_2;

import java.util.List;

/**
 *
 * @author Telwa
 */
public class helper {
    
    
    
    public static int findmin(List<Pixel> list )
    {
          double indexvalue = list.get(0).getHidden();
            int indexI;
            indexI = list.indexOf(0);
            int s = list.size();
            for(int i=0; i <s; i++)
            {
                double v = list.get(i).getHidden();
                 if (indexvalue < v) {
                    indexvalue = v;
                    indexI= i;
                    }
                 // System.out.println(indexI);
            }
        return indexI;
    
}}
