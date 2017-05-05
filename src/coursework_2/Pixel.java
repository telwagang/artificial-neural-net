package coursework_2;

/**
 *
 * @author Telwa
 */
public class Pixel {
    	private int p;

	private double f;

	public Pixel(int i) {
		this.p = i;
	}

	public Pixel(double f) {
		this.f = f;
	}

	public int getPixel() {
		return p;
	}

	public double getHidden() {
		return f;
	}
    
}
