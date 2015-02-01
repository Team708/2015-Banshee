package org.team708.robot.commands.claw;

import org.team708.robot.Robot;
import org.team708.robot.subsystems.ClawElevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClawHeightDecrement extends Command {

    public ClawHeightDecrement() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.clawElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	/* 
    	 * Sets the PID setpoint to the lower limit if the robot is within a tote height of
    	 * of the lower limit, or decrements it by the height of a tote if it isn't
    	 */
    	if(Robot.clawElevator.getPosition() <= 
    			(ClawElevator.LOWER_LIMIT + ClawElevator.TOTE_HEIGHT)) {
    		Robot.clawElevator.setSetpoint(ClawElevator.LOWER_LIMIT);
    	} else {
    		Robot.clawElevator.setSetpoint(Robot.clawElevator.getPosition() - ClawElevator.TOTE_HEIGHT);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
