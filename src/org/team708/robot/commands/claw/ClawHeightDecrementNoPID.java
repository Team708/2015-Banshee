package org.team708.robot.commands.claw;

import org.team708.robot.Robot;
import org.team708.robot.subsystems.ClawElevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClawHeightDecrementNoPID extends Command {

	private static final double movespeed = -1.0;
	private static final double stopspeed = 0.0;
	private boolean atLimit;
	private double initialPosition;
	
    public ClawHeightDecrementNoPID() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.clawElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	// Checks to see if the elevaotr is within a tote height of the lower limit
    	atLimit = (Robot.clawElevator.getPosition() <= 
    			(ClawElevator.LOWER_LIMIT + ClawElevator.TOTE_HEIGHT));
    	
    	// If it is, stop the command ASAP
    	if(atLimit) {
    		cancel();
    	}
    	
    	// Records initial position
    	initialPosition = Robot.clawElevator.getPosition();
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	/*
    	 * Sets the motor to move at movespeed, provided
    	 * initialize() has no already canceled the command due to being at the limit
    	 */
    	Robot.clawElevator.setElevatorSpeed_Manual(movespeed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.clawElevator.getPosition() >= 
        		(initialPosition - ClawElevator.TOTE_HEIGHT));
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	// Stops the motor upon reaching tote decrement
    	Robot.clawElevator.setElevatorSpeed_Manual(stopspeed);
    
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	// Stops the motor when at limit
    	Robot.clawElevator.setElevatorSpeed_Manual(stopspeed);
    
    }
}
