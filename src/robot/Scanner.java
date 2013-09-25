/**
 * 
 */
package robot;

import java.util.ArrayList;

import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;

/**
 * @author khoatran
 *
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
	 * 
	 */
	public Scanner(NXTRegulatedMotor theMotor, LightSensor theEye) {
		motor = theMotor;
		motor.setSpeed(500);
		motor.setAcceleration(4000);
		eye = theEye;
		eye.setFloodlight(false);
	}
	
	/**
	 * 
	 * @param limit
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
	 * rotate the scanner head to the angle
	 * 
	 * @param angle
	 * @param instantReturn
	 *            if true, the method is non-blocking
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
