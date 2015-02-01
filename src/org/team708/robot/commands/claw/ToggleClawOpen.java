package org.team708.robot.commands.claw;

import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleClawOpen extends Command {

    public ToggleClawOpen() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	// Sets the claw to closed if it is open
    	if(Robot.claw.isClawOpen()) {
    		Robot.claw.closeClaw();
    	} 
    	// Sets the claw to open if it is closed
    	else if(Robot.claw.isClawClosed()) {
    		Robot.claw.openClaw();
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
