package org.team708.robot.subsystems;

import org.team708.robot.Constants;
import org.team708.robot.RobotMap;
import org.team708.robot.commands.indexer.JoystickIndexerControl;
import org.team708.robot.util.IRSensor;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem that carries up totes on a chain system.
 * @author jlwang
 * @author omn0mn0m
 */
public class Indexer extends Subsystem {

	private CANTalon indexerMotorLeft;		// Spike for the indexer motor
//	private CANTalon indexerMotorRight;
	
	//Whether elevator has been lowered
	public boolean indexerDown = false;
	
	//number of tote heights/totes indexed
	public int toteCount;
	
	//Encoder
	private Encoder indexerEncoder;
	private double distancePerPulse;
	
	//IR Sensor
	private IRSensor indexerIR;
	
	public Indexer() {
		//Creates motors that run elevator
		indexerMotorLeft = new CANTalon(RobotMap.indexerMotor);
//		indexerMotorRight = new CANTalon(RobotMap.indexerMotorRight);
		
		//Creates encoders for elevator motors
		indexerEncoder = new Encoder(RobotMap.indexerEncoderA, RobotMap.indexerEncoderB);

		distancePerPulse = (Constants.INDEXER_SPROCKET_DIAMETER * Math.PI) / (Constants.GRAYHILL_ENCODER_PULSES_PER_REVOLUTION);

		indexerEncoder.setDistancePerPulse(distancePerPulse);
		indexerEncoder.setReverseDirection(true);
		
		indexerIR = new IRSensor(RobotMap.indexerIRSensor, IRSensor.GP2Y0A21YK0F);
	}
	
	/*
	 * Gets the distance the elevator has moved
	 */
	public double getEncoderDistance() {
		return indexerEncoder.getDistance();
	}
	
	/*
	 * Gets the speed the encoder measures
	 */
	public double getEncoderRate() {
		return indexerEncoder.getRate();
	}
	
	public double getEncoderCount() {
		return indexerEncoder.get();
	}
	
	/*
	 * Resets the encoder count
	 */
	public void resetEncoder() {
		indexerEncoder.reset();
	}
	
	public double getIRDistance() {
		return indexerIR.getClippedAverageDistance();
	}
	
	/**
	 * Raises the tote
	 */
	public void raiseIndexer() {
		// NOTE: The motors on the indexer's gearbox run reverse of each other
		indexerMotorLeft.set(Constants.MOTOR_REVERSE);
//		indexerMotorRight.set(Constants.MOTOR_FORWARD);
	}
	
	/**
	 * Lowers the tote
	 */
	public void lowerIndexer() {
		// NOTE: The motors on the indexer's gearbox run reverse of each other
		indexerMotorLeft.set(Constants.MOTOR_FORWARD);
//		indexerMotorRight.set(Constants.MOTOR_REVERSE);
	}
	
	public void move(double speed) {
		indexerMotorLeft.set(-speed);
	}
	
	/**
	 * Turns off the indexer so it does not move
	 */
	public void stopIndexer() {
		indexerMotorLeft.set(Constants.MOTOR_OFF);
//		indexerMotorRight.set(Constants.MOTOR_OFF);
	}
	
	/**
	 * Gets the number of tote increments that have been raised
	 */
	public int getToteCount() {
		return toteCount;
	}
	
	/**
	 * Sets the number of totes collected
	 * @param toteCount
	 */
	public void setToteCount(int toteCount) {
		this.toteCount = toteCount;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new JoystickIndexerControl());
    }
    
    /**
     * Sends data about the subsystem to the Smart Dashboard
     */
    public void sendToDashboard() {
    	if (Constants.DEBUG) {
    		SmartDashboard.putNumber("indexer Encoder Count", getEncoderCount());
    	}
    	
    	SmartDashboard.putNumber("indexer Encoder Distance", getEncoderDistance());
    	SmartDashboard.putNumber("indexer Tote Count", getToteCount());
    	SmartDashboard.putNumber("indexer IR Distance", getIRDistance());
    }
}

