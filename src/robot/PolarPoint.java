/**
 * 
 */
package robot;

/**
 * @author khoatran
 *
 */
public class PolarPoint {

	/**
	 * Instance variables
	 */
	private int distance;
	private int angle;
	
	/**
	 * Constructs a new polar point
	 */
	public PolarPoint(int r, int a) {
		distance = r;
		angle = a;
	}
	
	/**
	 * @return distance of polar point
	 */
	public int getDistance() {
		return distance;
	}
	
	/**
	 * @return angle of polar point
	 */
	public int getAngle() {
		return angle;
	}
	
	/**
	 * Sets the distance of polar point
	 * @param dist
	 */
	public void setDistance(int dist) {
		distance = dist;
	}
	
	/**
	 * Sets the angle of polar point
	 * @param ang
	 */
	public void setAngle(int ang) {
		angle = ang;
	}
}
