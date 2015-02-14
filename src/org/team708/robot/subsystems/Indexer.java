package org.team708.robot.subsystems;

import org.team708.robot.Constants;
import org.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Indexer extends Subsystem {

	private Relay indexerMotor;		// Spike for the indexer motor
	
	//Whether elevator has been lowered
	public boolean indexerDown = false;
	
	//number of tote heights/totes indexed
	public int toteCount;
	
	//Digital sensors
	private Encoder indexerEncoder;
	private double distancePerPulse;
	
	public Indexer() {
		//Creates motors that run elevator
		indexerMotor = new Relay(RobotMap.indexerMotor);
		
		//Creates encoders for elevator motors
		indexerEncoder = new Encoder(RobotMap.indexerEncoderA, RobotMap.indexerEncoderB);
		distancePerPulse = (Constants.INDEXER_GEARING * Constants.INDEXER_SPROCKET_DIAMETER * Math.PI) / (Constants.INDEXER_ENCODER_PULSES_PER_REV);
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
	
	/*
	 * Checks if tote is at height yet
	 */
	public boolean encoderTop() {
		if (getEncoderDistance() < Constants.TOTE_HEIGHT) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean encoderBottom() {
		if (getEncoderDistance() > -Constants.TOTE_HEIGHT) {
			return false;
		} else {
			return true;
		}
	}
	
	/*
	 * Raises the tote
	 */
	public void raiseIndexer() {
		indexerMotor.set(Constants.SPIKE_FORWARD);
	}
	
	/*
	 * Lowers the tote
	 */
	public void lowerIndexer() {
		indexerMotor.set(Constants.SPIKE_REVERSE);
	}
	
	/*
	 * Does not move the tote
	 */
	public void stopIndexer() {
		indexerMotor.set(Constants.SPIKE_OFF);
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
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void sendToSmartDashboard() {
    	SmartDashboard.putNumber("Encoder Count", getEncoderCount());
    	SmartDashboard.putNumber("Encoder Distance", getEncoderDistance());
    }
}

