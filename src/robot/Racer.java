package robot;

import lejos.nxt.Motor;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;

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
	public DifferentialPilot pilot;

	/**
	 * 
	 */
	public Racer(Scanner s, DifferentialPilot dp) {
		scanner = s;
		pilot = dp;
		originalDistance = trackLength * ftToCm;
	}
	
	public void toLight(double angle) {
		while (scanner.maxLight() > 30){
			pilot.steer(scanner.getAngle());
		}
	}

}
