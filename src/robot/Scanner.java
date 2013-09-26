package robot;

import java.util.ArrayList;

import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;

/**
 * Scanner class which implements logic for scanning lights and obstacles
 * Adapted from ScanRecorder.java in the skeleton
 * @author Corey Short, Khoa Tran
 */
public class Scanner {

	/**
	 * Instance variables
	 */
	private NXTRegulatedMotor motor;
	private LightSensor eye;
	public int xAngle = 0;
	public int xLight = 0;
	
	/**
	 * Constructor for Scanner class that takes in the motor and lightsensor as params
	 * @param theMotor - motor of the robot
	 * @param theEye - light sensor to detect the lights
	 */
	public Scanner(NXTRegulatedMotor theMotor, LightSensor theEye) {
		motor = theMotor;
		motor.setSpeed(500);
		motor.setAcceleration(4000);
		eye = theEye;
		eye.setFloodlight(false);
	}
	
	/**
	 * Scans to a certain angle
	 * @param limit - the angle to scan to
	 */
	public void scanTo(int limit) {
		int light;
		xLight = 0;
		xAngle = motor.getTachoCount();
		motor.rotateTo(limit, true);
		while (motor.isMoving()) {
			light = eye.getLightValue();
			if (light > xLight) {
				xLight = light;
				xAngle = motor.getTachoCount();
			}
		}
	}
	
	/**
	 * Rotates the scanner head to the angle
	 * @param angle - how much to rotate to
	 * @param instantReturn - if true, we don't wait until the whole rotate process completes
	 */
	public void rotateTo(int angle, boolean instantReturn) {
		motor.rotateTo(angle, instantReturn);
	}
	
	/**
	 * Scans for obstacles - Milestone 2
	 * @param angle - the angle to scan at
	 */
	public void scanObstacle(int angle) {
	}
}
