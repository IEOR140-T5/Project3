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
		motor.setSpeed(500);
		motor.setAcceleration(4000);;
		eye = theEye;
		eye.setFloodlight(false);
		highestLight = new PolarPoint(0, 0);
	}
	
	/**
	 * Scans for lights and obstacles, rotating from -45 to 45
	 */
	public void scan() {
		
		int startAngle = -3;
		motor.rotateTo(startAngle, true);
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
	
	public int xAngle = 0;
	public int xLight = 0;
	
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
	 * Gets light location
	 */
	public PolarPoint getLights() {
		return highestLight;
	}
	
	/**
	 * Scans for obstacles - Milestone 2
	 * @param angle - the angle to scan at
	 */
	public void scanObstacle(int angle) {
	}
}
