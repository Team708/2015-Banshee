package org.team708.robot.subsystems;

import org.team708.robot.RobotMap;
import org.team708.robot.util.IRSensor;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ToteElevator extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//Motor controllers
	private CANTalon toteElevatorMotor1; 
	private CANTalon toteElevatorMotor2;
	public final double RAISE_SPEED = 1.0;
	public final double LOWER_SPEED = -1.0;
	
	//Whether elevator has been lowered
	public boolean elevatorDown = false;
	
	//number of tote heights/totes indexed
	public int toteCount;
	
	//max number of tote heights allowed to raise
	public final int TOTE_UPPER_LIMIT = 6;
	
	//Digital sensors
	private Encoder toteElevatorEncoder;
	private final double PULSES_PER_REVOLUTION = 7.0;
	private final double GEARING = (16 / 50);
	private double distancePerPulse;
	
	public final double TOP_ENCODER_DISTANCE = 25.0;
	public final double BOTTOM_ENCODER_DISTANCE = 0.0;
	
	//Analog sensors
	private IRSensor toteElevatorIRSensor;
	private final double HAS_TOTE_DISTANCE_CLOSE = 0;
	private final double HAS_TOTE_DISTANCE_FAR = 3;
	
	public ToteElevator() {
		//Creates motors that run elevator
		toteElevatorMotor1 = new CANTalon(RobotMap.toteElevatorMotor1);
		toteElevatorMotor2 = new CANTalon(RobotMap.toteElevatorMotor2);
		
		//Creates encoders for elevator motors
		toteElevatorEncoder = new Encoder(RobotMap.toteElevatorEncoderA, RobotMap.toteElevatorEncoderB);
		distancePerPulse = PULSES_PER_REVOLUTION * GEARING;
		toteElevatorEncoder.setDistancePerPulse(distancePerPulse);
		toteElevatorEncoder.setReverseDirection(true);
		
		//Creates IR sensor
		toteElevatorIRSensor = new IRSensor(RobotMap.toteElevatorIRSensor, IRSensor.GP2Y0A21YK0F);
		toteElevatorIRSensor.setTriggerBounds(HAS_TOTE_DISTANCE_CLOSE, HAS_TOTE_DISTANCE_FAR, false);

	}
	/*
	 * Sets speed of motors to raise or lower totes
	 */
	public void set(double speed) {
		toteElevatorMotor1.set(speed);
		toteElevatorMotor2.set(-speed);
	}
	/*
	 * Gets the distance the elevator has moved
	 */
	public double getEncoderDistance() {
		return toteElevatorEncoder.getDistance();
	}
	/*
	 * Gets the speed the encoder measures
	 */
	public double getEncoderRate() {
		return toteElevatorEncoder.getRate();
	}
	/*
	 * Resets the encoder count
	 */
	public void resetEncoder() {
		toteElevatorEncoder.reset();
	}
	/*
	 * Checks if tote is at height yet
	 */
	public boolean encoderTop() {
		if (getEncoderDistance() < TOP_ENCODER_DISTANCE) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean encoderBottom() {
		if (getEncoderDistance() > -TOP_ENCODER_DISTANCE) {
			return false;
		} else {
			return true;
		}
	}
	
	/*
	 * Checks if tote is in elevator
	 */
	public boolean hasTote() {
		return toteElevatorIRSensor.isTriggered();
	}
	/*
	 * Raises the tote
	 */
	public void raiseTote() {
		this.set(RAISE_SPEED);

	}
	
	/*
	 * Lowers the tote
	 */
	public void lowerTote() {
		this.set(LOWER_SPEED);
	}
	/*
	 * Does not move the tote
	 */
	public void stopTote() {
		this.set(0.0);
	}
	/*
	 * Gets the number of tote increments that have been raised
	 */
	public int getToteCount() {
		return toteCount;
	}
	
	public void setToteCount(int toteCount) {
		this.toteCount = toteCount;
	}

	//raises tote when y is pressed
	public void YToteUp() {
		
	}
	
	//lowers tote when a is pressed	
	public void AToteDown() {

	}
		
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

