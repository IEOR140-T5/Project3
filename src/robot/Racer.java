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
	public static final double THRESHOLD = 55; 

	/**
	 * Instance variables
	 */
	private Scanner scanner;
	private DifferentialPilot pilot;
	private int distanceLimit = 50;

	/**
	 * Constructor for Racer, which takes in a Scanner and a DifferentialPilot
	 * @param s - Scanner equips with lights and obstacles detection           
	 * @param dp - Pilot to control the motor       
	 */
	public Racer(Scanner s, DifferentialPilot dp) {
		scanner = s;
		pilot = dp;
	}

	/**
	 * Completes two round trips for Milestone 1 by going to the lights back and forth
	 */
	public void toLight() {
		for (int i = 0; i < 2; i++) {
			while (scanner.getLight() < THRESHOLD) {
				//scanner.rotateTo(-30, true);
				scanner.scanTo(60);
				toAngle(scanner.getAngle());
				LCD.drawInt((int) scanner.getLight(), 0, 0);

				if (scanner.getLight() > THRESHOLD) {
					stopRobot();
					sleepRobot(500);
					break;
				}
				scanner.scanTo(-60);
				toAngle(scanner.getAngle());
				LCD.drawInt((int) scanner.getLight(), 0, 0);
				if (scanner.getLight() > THRESHOLD) {
					stopRobot();
					sleepRobot(500);
				}
			}
			turnAround();
			scanner.setLight(0);

			// Move back
			while (scanner.getLight() < THRESHOLD) {
				//scanner.rotateTo(-30, true);
				scanner.scanTo(60);
				toAngle(scanner.getAngle());
				LCD.drawInt((int) scanner.getLight(), 0, 0);
				
				if (scanner.getLight() > THRESHOLD) {
					stopRobot();
					sleepRobot(500);
					break;
				}
				scanner.scanTo(-60);
				toAngle(scanner.getAngle());
				LCD.drawInt((int) scanner.getLight(), 0, 0);
				if (scanner.getLight() > THRESHOLD) {
					stopRobot();
					sleepRobot(500);
				}
			}
			turnAround();
			scanner.setLight(0);
		}
		
		scanner.scanTo(0);
	}

	/**
	 * Main method for Milestone 2
	 */
	public void findLight(){
		scanner.rotateTo(0, true);
		Detector detector = new Detector();
		detector.start();
				
		while (!detector.isDetected){
			scanner.scanTo(60);
			toAngle(scanner.getAngle());
			LCD.drawInt((int) scanner.getLight(), 0, 0);
			scanner.scanTo(-60);
			toAngle(scanner.getAngle());
			LCD.drawInt((int) scanner.getLight(), 0, 0);
			if (detector.isDetected){
				whenDetected();
			}
			Thread.yield();
		}
		
	}

	/**
	 * Tasks to perform when object is detected
	 */
	public void whenDetected() {
		stopRobot();
		sleepRobot(1000);
		pilot.travel(-10);
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
		pilot.rotate(200);
		scanner.rotateTo(0, false);
		pilot.steer(0); // travel straight
	}
	

    /**
     *here is the inner class definition
     */
    class Detector extends Thread {

        boolean isDetected = false; 

        public void run(){
            while (!isDetected){
            	LCD.drawInt((int) getDistance(), 5, 5);
            	
            	if (getDistance() < distanceLimit ){
            		isDetected = true;
            	} 
            	if (isLeftTouched() || isRightTouched()){
            		isDetected = true;
            	}
            Thread.yield();
            }
            //isDetected = false;
        }
        
        /*
         * Return distance from the reading from Ultrasonic Sensor
         */
        
    	public int getDistance(){
    		return scanner.getUltrasonicSensor().getDistance();
    	}
    	
    	/*
         * Return True/False when the Left Touch Sensor is touched
         */
    	public boolean isLeftTouched(){
    		return scanner.getLeftTouchSensor().isPressed();
    	}
    	
    	/*
         * Return True/False when the Right Touch Sensor is touched
         */
    	public boolean isRightTouched(){
    		return scanner.getRightTouchSensor().isPressed();
    	}
    }

}
