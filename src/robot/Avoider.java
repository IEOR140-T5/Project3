package robot;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.util.Delay;
import java.util.Random;

/**
 * @author khoatran
 *
 */
public class Avoider {

	private Racer racer;
	private Scanner scanner;
	private int _turnAngle = 45;
	public static final int OBJECT_THRESHOLD = 30;
	
	/**
	 * Public constructor for Avoider class
	 */
	public Avoider(Racer racer, Scanner scanner) {
		this.racer = racer;
		this.scanner = scanner;
	}
	
	public void avoid(){
		int objectAngle;
		racer.travel(-15);
		scanner.scanObject(racer._scanAngle);
		scanner.rotateTo(0, true);
		objectAngle = scanner.getAngle();
		int minDistanceToObject = scanner.getMinDistance();
		
		LCD.clear();
		LCD.drawInt((int) objectAngle, 0, 0);
		
		//boolean tooCloseToLeft = objectAngle > 0;
		boolean tooCloseToRight = objectAngle < 0;
		
		// right
		if (tooCloseToRight) {
			racer.turnPilot(_turnAngle);
		} else { // left
			racer.turnPilot(-_turnAngle);
		}
		//Delay.msDelay(200);
		racer.travel(25);
	}
}
