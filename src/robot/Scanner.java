/**
 * 
 */
package robot;

import java.awt.Point;
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
	 * 
	 */
	public void scan() {
		
	}
	
	public void scanLights(int angle) {
		int lv = eye.getLightValue();
		if (lv > highestLight.getDistance()) {
			highestLight.setDistance(lv);
			highestLight.setAngle(angle);
		}
	}
}
