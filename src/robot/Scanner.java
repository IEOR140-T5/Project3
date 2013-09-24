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
	private PolarPoint highestLight;
	
	/**
	 * 
	 */
	public Scanner(NXTRegulatedMotor theMotor, LightSensor theEye) {
		motor = theMotor;
		eye = theEye;
		eye.setFloodlight(false);
		highestLight = new PolarPoint(0, 0);
	}
	
	/**
	 * Scans for lights and obstacles, rotating from -45 to 45
	 */
	public void scan() {
		int startAngle = -45;
		motor.rotateTo(startAngle);
		int oldAngle = motor.getTachoCount();
		
		// Scan from -45 to 45
		while (motor.isMoving()) {
			motor.rotate(90, true);
			int angle = motor.getTachoCount();
			if (angle != oldAngle) {
				scanLights(angle);
			}
		}
		
		// Scan the other way from 45 to -45
		while (motor.isMoving()) {
			motor.rotate(-90, true);
			int angle = motor.getTachoCount();
			if (angle != oldAngle) {
				scanLights(angle);
			}
		}
	}
	
	/**
	 * Scans for lights
	 * @param angle - the angle to scan at
	 */
	public void scanLights(int angle) {
		int lv = eye.getLightValue();
		if (lv > highestLight.getMaxLight()) {
			highestLight.setMaxLight(lv);
			highestLight.setAngle(angle);
		}
	}
	
	/**
	 * Gets light location
	 */
	public PolarPoint getLights() {
		return highestLight;
	}
	
	/**
	 * Scans for obstacles - Milestone 2
	 * @param angle
	 */
	public void scanObstacle(int angle) {
	}
}
