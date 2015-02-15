package org.team708.robot.subsystems;

import org.team708.robot.Constants;
import org.team708.robot.RobotMap;

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
	private CANTalon indexerMotorRight;
	
	//Whether elevator has been lowered
	public boolean indexerDown = false;
	
	//number of tote heights/totes indexed
	public int toteCount;
	
	//Digital sensors
	private Encoder indexerEncoder;
	private double distancePerPulse;
	
	public Indexer() {
		//Creates motors that run elevator
		indexerMotorLeft = new CANTalon(RobotMap.indexerMotorLeft);
		indexerMotorRight = new CANTalon(RobotMap.indexerMotorRight);
		
		//Creates encoders for elevator motors
		indexerEncoder = new Encoder(RobotMap.indexerEncoderA, RobotMap.indexerEncoderB);
<<<<<<< HEAD
		distancePerPulse = (Constants.INDEXER_SPROCKET_DIAMETER * Math.PI) / (Constants.INDEXER_ENCODER_PULSES_PER_REV);
=======
		distancePerPulse = (Constants.INDEXER_SPROCKET_CIRCUMERENCE) / (Constants.INDEXER_ENCODER_PULSES_PER_REV);
>>>>>>> origin/develop
		indexerEncoder.setDistancePerPulse(distancePerPulse);
		indexerEncoder.setReverseDirection(true);
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
	
	/**
	 * Raises the tote
	 */
	public void raiseIndexer() {
		// NOTE: The motors on the indexer's gearbox run reverse of each other
		indexerMotorLeft.set(Constants.MOTOR_REVERSE);
		indexerMotorRight.set(Constants.MOTOR_FORWARD);
	}
	
	/**
	 * Lowers the tote
	 */
	public void lowerIndexer() {
		// NOTE: The motors on the indexer's gearbox run reverse of each other
		indexerMotorLeft.set(Constants.MOTOR_FORWARD);
		indexerMotorRight.set(Constants.MOTOR_REVERSE);
	}
	
	/**
	 * Turns off the indexer so it does not move
	 */
	public void stopIndexer() {
		indexerMotorLeft.set(Constants.MOTOR_OFF);
		indexerMotorRight.set(Constants.MOTOR_OFF);
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
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Sends data about the subsystem to the Smart Dashboard
     */
    public void sendToSmartDashboard() {
    	SmartDashboard.putNumber("indexer Encoder Count", getEncoderCount());
    	SmartDashboard.putNumber("indexer Encoder Distance", getEncoderDistance());
    }
}

