package org.team708.robot.subsystems;

import org.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A lead screw based elevator to move the claw and container up and down
 */
public class ClawElevator extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	/* The maximum and minimum heights, approximately in tote heights
	 * but exactly in the number of limit switches tripped
	 */
	public static final int UPPER_LIMIT = 5;
	public static final int LOWER_LIMIT = 0;
	
	
	// Constant speeds to set the motor to
	private static final double UP_SPEED = 1.0;
	private static final double DOWN_SPEED = -1.0;
	private static final double STOPPED_SPEED = 0.0;
	
	// The current height of the claw
	private int containerHeight;
	
	// Limit switches for counting the height of the claw
	private static DigitalInput upperSwitch;
	private static DigitalInput switchSeries;
	private static DigitalInput lowerSwitch;
	
	// Motor for the lead screw
	private static CANTalon motor;
	
	public ClawElevator() {
		
		// Initializes the limit switches
		upperSwitch = new DigitalInput(RobotMap.clawElevatorUpperLimit);
		switchSeries = new DigitalInput(RobotMap.clawElevatorSeries);
		lowerSwitch = new DigitalInput(RobotMap.clawElevatorLowerLimit);
		
		// Initializes the motor
		motor = new CANTalon(RobotMap.clawElevatorMotor);
		
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
	 * 
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
	 * Sets container height to maximum, for when the upper limit is pressed
	 */
	public void setContainerHeightMax() {
		containerHeight = UPPER_LIMIT;
	}
	
	/**
	 * Sets the container height to minimum, for when the lower limit is pressed
	 */
	public void setContainerHeightMin() {
		containerHeight = LOWER_LIMIT;
	}
	
	/**
	 * Sets the elevator to move up
	 */
	public void moveUp() {
		motor.set(UP_SPEED);
	}
	
	/**
	 * Sets the elevator to move down
	 */
	public void moveDown() {
		motor.set(DOWN_SPEED);
	}
	
	/**
	 * Stops the elevator
	 */
	public void stop() {
		motor.set(STOPPED_SPEED);
	}
	
	public void sendToDashboard() {
		SmartDashboard.putBoolean("Lower Switch", getLowerSwitch());
		SmartDashboard.putBoolean("Switch Series", getSeries());
		SmartDashboard.putBoolean("Upper Switch", getUpperSwitch());
	}
}

