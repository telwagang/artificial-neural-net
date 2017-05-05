/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KNN;

import coursework_2.Pixel;

/**
 *
 * @author Telwa
 */
public class kpixel extends Pixel {
    
    private double distance; 
    
    public kpixel(int i) {
        super(i);
    }
     public kpixel(double i) {
        super(i);
    }
    /**
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }
    /**
     * @param distance the distance to set
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }
}
