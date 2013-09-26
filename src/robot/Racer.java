package robot;

import lejos.nxt.LCD;
import lejos.robotics.navigation.DifferentialPilot;

/**
 * Racer class that implements the logic for robot going toward the light
 * @author Khoa Tran, Phuoc Nguyen, Corey Short
 */
public class Racer {

	/**
	 * Static variables
	 */
	public static final int TRACKLENGTH = 20; // in ft
	public static final double FTTOCM = 30.48;
	public static final double THRESHOLD1 = 63; // the one with yellow mark
	public static final double THRESHOLD = 60;

	/**
	 * Instance variables
	 */
	private Scanner scanner;
	private double originalDistance = 0;
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
	 * Completes two round trips for Milestone 1 by going to the lights back and forth
	 */
	public void toLight() {
		for (int i = 0; i < 2; i++) {
			while (scanner.xLight < THRESHOLD1) {
				scanner.rotateTo(-30, true);
				scanner.scanTo(60);
				toAngle(scanner.xAngle);

				if (scanner.xLight > THRESHOLD1) {
					stopRobot();
					sleepRobot(300);
					break;
				}
				scanner.scanTo(-60);
				toAngle(scanner.xAngle);
				LCD.drawInt((int) scanner.xLight, 0, 0);
				if (scanner.xLight > THRESHOLD1) {
					stopRobot();
					sleepRobot(300);
				}
			}
			turnAround();
			scanner.xLight = 0;

			// Move to the other light with a different threshold
			while (scanner.xLight < THRESHOLD) {
				scanner.rotateTo(-30, true);
				scanner.scanTo(60);
				toAngle(scanner.xAngle);

				if (scanner.xLight > THRESHOLD1) {
					stopRobot();
					sleepRobot(300);
					break;
				}
				scanner.scanTo(-60);
				toAngle(scanner.xAngle);
				LCD.drawInt((int) scanner.xLight, 0, 0);
				if (scanner.xLight > THRESHOLD) {
					stopRobot();
					sleepRobot(300);
				}
			}
			turnAround();
			scanner.xLight = 0;
		}
		scanner.scanTo(0);
	}

	/**
	 * Stops the robot
	 */
	public void stopRobot() {
		pilot.stop();
	}

	/**
	 * Sleeps the robot
	 * @param ms - how long to sleep
	 */
	public void sleepRobot(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			System.out.println(e);
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
		pilot.rotate(220);
		pilot.steer(0); // travel straight
	}
}
