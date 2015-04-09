package org.team708.robot.subsystems;

import org.team708.robot.Constants;
import org.team708.robot.RobotMap;
import org.team708.robot.commands.clawElevator.JoystickMoveClawElevator;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A lead screw based elevator to move the claw and container up and down
 * @author katzekitteh
 * @author omn0mn0m
 */
public class ClawElevator extends Subsystem {
	
	private int containerHeight;		// The current height of the claw
	
	private Encoder clawElevatorEncoder;			// Encoder for intermediate travel
	private double distancePerPulse;
	
	private DigitalInput upperSwitch, lowerSwitch;	// Limit switches for the top and bottom of the travel
	
	private static CANTalon clawElevatorMotor;		// Motor for the lead screw
//	private static Talon clawElevatorMotor;
	
	/**
	 * Constructor
	 */
	public ClawElevator() {
		// Initializes the encoder
		clawElevatorEncoder = new Encoder(RobotMap.clawElevatorEncoderA, RobotMap.clawElevatorEncoderB);
		distancePerPulse = Constants.CLAW_ELEVATOR_SPROCKET_DIAMETER * Math.PI / Constants.GRAYHILL_ENCODER_PULSES_PER_REVOLUTION;
		clawElevatorEncoder.setDistancePerPulse(distancePerPulse);
		clawElevatorEncoder.reset();
		
		// Initialises the switches
		upperSwitch = new DigitalInput(RobotMap.clawElevatorUpperSwitch);
		lowerSwitch = new DigitalInput(RobotMap.clawElevatorLowerSwitch);
		
		// Initializes the motor
		clawElevatorMotor = new CANTalon(RobotMap.clawElevatorMotor);
//		clawElevatorMotor = new Talon(0);
		
	}

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new JoystickMoveClawElevator());
    }
	
	/**
	 * Returns true if the upper switch is pressed
	 * @return At upper limit
	 */
	public boolean getUpperSwitch() {
		return !upperSwitch.get();   // not because default is closed, stops if circuit is broken
	}
	
	/**
	 * Returns true if the lower switch is pressed
	 * @return At lower limit
	 */
	public boolean getLowerSwitch() {
		return !lowerSwitch.get(); // not because default is closed, stops if circuit is broken
	}
	
	/**
	 * Resets the encoder on the claw elevator
	 */
	public void resetEncoder() {
		clawElevatorEncoder.reset();
	}
	
	/**
	 * Returns the encoder reading for the distance traveled
	 * @return
	 */
	public double getTravelDistance() {
		return clawElevatorEncoder.getDistance();
	}
	
	/**
	 * Returns the raw encoder count
	 * @return
	 */
	public double getEncoderCount() {
		return clawElevatorEncoder.get();
	}
	
	/**
	 * Returns if the claw elevator has reached the stopping point
	 * @param point
	 * @return
	 */
	public boolean isAtIntemediateStop(int point, boolean goingUp) {
		if (goingUp) {
			return Math.abs(getTravelDistance()) >= Constants.CLAW_ELEVATOR_UP_TRAVEL_DISTANCES[point];
		} else {
			return Math.abs(getTravelDistance()) >= Constants.CLAW_ELEVATOR_DOWN_TRAVEL_DISTANCE[point];
		}
	}
	
	/**
	 * Returns the height of the claw in regards to totes
	 * @return The current height of the claw, as measured by the limit switches
	 */
	public int getContainerHeight() {
		return containerHeight;
	}
	
	public void setContainerHeight(int containerHeight) {
		this.containerHeight = containerHeight;
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
	
	public void manualMove(double speed) {
		clawElevatorMotor.set(speed);
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
		SmartDashboard.putBoolean("Upper Switch", getUpperSwitch());
		SmartDashboard.putNumber("Travel Distance", getTravelDistance());
		SmartDashboard.putNumber("Container Height", getContainerHeight());
		
//		if (Constants.DEBUG) {
			SmartDashboard.putNumber("Claw Encoder Count", getEncoderCount());
			SmartDashboard.putBoolean("Claw Stopped", clawElevatorEncoder.getStopped());
//		}
	}
}

