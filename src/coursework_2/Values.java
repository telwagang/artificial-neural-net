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
public class Values {
    
	private final int imageValue;
	private List<Pixel> Pixels;

	public Values(int value) {
		this.imageValue = value;
	}

	public int getImageNumber() {
		return imageValue;
	}

	public void setPixel(List<Pixel> pixel) {
		this.Pixels = pixel;
	}

	public List<Pixel> getPixels() {
		return Pixels;
	}

	@Override
	public String toString() {
		return "" + imageValue + "";
	}
        public boolean equals(Object o) {
        Object target = o;
        if (o instanceof Values) {
            target = ((Values) o).Pixels;
        }
        return this.equals(target);
    }

}
