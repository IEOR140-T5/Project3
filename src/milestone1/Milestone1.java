/**
 * 
 */
package milestone1;

import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.Sound;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.util.ButtonCounter;
import lejos.robotics.navigation.DifferentialPilot;
import robot.*;

/**
 * @author Khoa Tran, Phuoc Nguyen
 *
 */
public class Milestone1 {

	/**
	 * 
	 */	
	
	public static void main(String args[]) {
		float wheelDiameter = 5.38f;
		float trackWidth = 11.2f;
		DifferentialPilot pilot = new DifferentialPilot(wheelDiameter,
				trackWidth, Motor.A, Motor.C);
		Scanner scanner = new Scanner(Motor.C, new LightSensor(SensorPort.S2));
		Racer racer = new Racer(scanner, pilot);
		Milestone1 milestone1 = new Milestone1();
		
		milestone1.go();
	}
	
	public void go(){
		
	}

}
