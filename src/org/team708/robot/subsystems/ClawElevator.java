package org.team708.robot.subsystems;

import org.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClawElevator extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public static final int UPPER_LIMIT = 5;
	public static final int LOWER_LIMIT = 0;
	
	private static final double UP_SPEED = 1.0;
	private static final double DOWN_SPEED = -1.0;
	private static final double STOPPED_SPEED = 0.0;
	
	private int containerHeight;
	
	private static DigitalInput upperSwitch;
	private static DigitalInput switchSeries;
	private static DigitalInput lowerSwitch;
	
	private static CANTalon motor;
	
	public ClawElevator() {
		
		upperSwitch = new DigitalInput(RobotMap.clawElevatorUpperLimit);
		switchSeries = new DigitalInput(RobotMap.clawElevatorSeries);
		lowerSwitch = new DigitalInput(RobotMap.clawElevatorLowerLimit);
		
		motor = new CANTalon(RobotMap.clawElevatorMotor);
		
	}

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	

	public boolean getUpperSwitch() {
		return upperSwitch.get();
	}
	
	public boolean getSeries() {
		return switchSeries.get();
	}
	
	public boolean getLowerSwitch() {
		return lowerSwitch.get();
	}
	
	public int getContainerHeight() {
		return containerHeight;
	}
	
	public void incrementContainerHeight() {
		containerHeight++;
	}
	
	public void decrementContainerHeight() {
		containerHeight--;
	}
	
	public void setContainerHeightMax() {
		containerHeight = UPPER_LIMIT;
	}
	
	public void setContainerHeightMin() {
		containerHeight = LOWER_LIMIT;
	}
	
	public void moveUp() {
		motor.set(UP_SPEED);
	}
	
	public void moveDown() {
		motor.set(DOWN_SPEED);
	}
	
	public void stop() {
		motor.set(STOPPED_SPEED);
	}
	
}

