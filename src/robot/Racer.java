package robot;
/**
 * 
 */

/**
 * @author khoatran
 *
 */
public class Racer {
	
	/**
	 * Static variables
	 */
	public static final int trackLength = 20; // in ft
	public static final double ftToCm = 30.48;
	
	/**
	 * Instance variables
	 */
	private Scanner scanner;
	private double originalDistance;

	/**
	 * 
	 */
	public Racer(Scanner s) {
		scanner = s;
		originalDistance = trackLength * ftToCm;
	}
	
	public void toLight() {
		
	}

}
