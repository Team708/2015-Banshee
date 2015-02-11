package org.team708.robot.subsystems;

import org.team708.robot.Constants;
import org.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A lead screw based elevator to move the claw and container up and down
 * @author katzekitteh
 * @author omn0mn0m
 */
public class ClawElevator extends Subsystem {
	
	private int containerHeight;		// The current height of the claw
	
	private DigitalInput upperSwitch;	// Top limit of the elevator
	private DigitalInput switchSeries;	// Multiple limit switches set in parallel
	private DigitalInput lowerSwitch;	// Lower limit of the elevator
	
	private static CANTalon clawElevatorMotor;		// Motor for the lead screw
	
	/**
	 * Constructor
	 */
	public ClawElevator() {
		
		// Initializes the limit switches
		upperSwitch = new DigitalInput(RobotMap.clawElevatorUpperLimit);
		switchSeries = new DigitalInput(RobotMap.clawElevatorSeries);
		lowerSwitch = new DigitalInput(RobotMap.clawElevatorLowerLimit);
		
		// Initializes the motor
		clawElevatorMotor = new CANTalon(RobotMap.clawElevatorMotor);
		
	}

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	/**
	 * Returns true if the upper switch is pressed
	 * @return At upper limit
	 */
	public boolean getUpperSwitch() {
		return upperSwitch.get();
	}
	
	/**
	 * Returns true if any of the switches in the series are pressed
	 * @return On series
	 */
	public boolean getSeries() {
		return !switchSeries.get();		// Inverse because the switch series is a series circuit running true when open
	}
	
	/**
	 * Returns true if the lower switch is pressed
	 * @return At lower limit
	 */
	public boolean getLowerSwitch() {
		return lowerSwitch.get();
	}
	
	/**
	 * Returns the height of the claw in regards to totes
	 * @return The current height of the claw, as measured by the limit switches
	 */
	public int getContainerHeight() {
		return containerHeight;
	}
	
	/**
	 * Increases container height 
	 * (For when a series switch is pressed && elevator moving up)
	 */
	public void incrementContainerHeight() {
		containerHeight++;
	}
	
	/**
	 * Decreases container height 
	 * (For when a series switch is pressed && elevator moving down)
	 */
	public void decrementContainerHeight() {
		containerHeight--;
	}
	
	/**
	 * Sets the elevator to move up
	 */
	public void moveUp() {
		clawElevatorMotor.set(Constants.MOTOR_FORWARD);
	}
	
	/**
	 * Sets the elevator to move down
	 */
	public void moveDown() {
		clawElevatorMotor.set(Constants.MOTOR_REVERSE);
	}
	
	/**
	 * Stops the elevator
	 */
	public void stop() {
		clawElevatorMotor.set(Constants.MOTOR_OFF);
	}
	
	/**
	 * Sends data to the Smart Dashboard
	 */
	public void sendToDashboard() {
		SmartDashboard.putBoolean("Lower Switch", getLowerSwitch());
		SmartDashboard.putBoolean("Switch Series", getSeries());
		SmartDashboard.putBoolean("Upper Switch", getUpperSwitch());
	}
}

