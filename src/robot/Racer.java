package robot;

import lejos.nxt.Motor;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;

/**
<<<<<<< HEAD
 * 
 */

/**
 * @author Khoa Tran, Phuoc Nguyen
 *
 */
public class Racer {
	
	/**
	 * Static variables
	 */
	public static final int TRACKLENGTH = 20; // in ft
	public static final double FTTOCM = 30.48;
	public static final int THRESHOLD = 30;
	
	/**
	 * Instance variables
	 */
	private Scanner scanner;
	private double originalDistance;
	private DifferentialPilot pilot;

	/**
	 * Constructor for Racer, which takes in a Scanner and a DifferentialPilot
	 * @param s - Scanner equips with lights and obstacles detection
	 * @param dp - Pilot to control the motor
	 */
	public Racer(Scanner s, DifferentialPilot dp) {
		scanner = s;
		pilot = dp;
		originalDistance = TRACKLENGTH * FTTOCM;
	}
	
	/**
	 * Completes two round trips for Milestone 1 by going to the lights
	 * back and forth
	 */
	public void toLight() {
		double distanceFromLight = originalDistance;
		for (int i = 0; i < 2; i++) {
			while (distanceFromLight > THRESHOLD) {
				scanner.scan();
				PolarPoint lightLocations = scanner.getLights();
				toAngle(lightLocations.getAngle());
				distanceFromLight = lightLocations.getMaxLight();
			}
			turnAround();
			distanceFromLight = originalDistance;
		}
	}
	
	/**
	 * Steers to a specific angle
	 * @param angle
	 */
	private void toAngle(double angle) {
		pilot.steer(angle);
	}
	
	/**
	 * Turns the robot around at the end of every half
	 */
	private void turnAround() {
		pilot.rotate(180);
		pilot.steer(0); // travel straight
	}

}
