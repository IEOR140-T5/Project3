package robot;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.util.Delay;

import java.util.Random;

/**
 * @author Khoa Tran, Phuoc Nguyen
 *
 */
public class Avoider {

	private Racer racer;
	private Scanner scanner;
	private int _turnAngle = 45;
	
	/**
	 * Public constructor for Avoider class
	 */
	public Avoider(Racer racer, Scanner scanner) {
		this.racer = racer;
		this.scanner = scanner;
	}
	
	public void avoid(int whichIsDetected){
		int objectAngle;
		racer.travel(-15);
		scanner.scanObject(racer._scanAngle);
		scanner.rotateTo(0, false);
		objectAngle = scanner.getAngle();
		
		LCD.clear();
		LCD.drawInt((int) objectAngle, 0, 0);
		
		boolean tooCloseToRight = objectAngle < 0;
		
		// This is when the ultra sensor detects an object nearby
		if (whichIsDetected == 1){
			// object is at right - beep once
			if (tooCloseToRight) {
				Sound.beep(); 
				racer.turnPilot(_turnAngle); // turn left
			} else { // object is at left - beep twice
				Sound.twoBeeps();
				racer.turnPilot(-_turnAngle); // turn right
			}
		// This is when the Touch Sensor detects
		} else {
			Sound.twoBeeps();Sound.twoBeeps();
			racer.turnPilot(_turnAngle);
		}
		//Delay.msDelay(200);
		racer.travel(25);
	}
}
