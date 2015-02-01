package org.team708.robot.commands.claw;

import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleWrist extends Command {

    public ToggleWrist() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	// Sets the wrist to horizontal position if it is vertical
    	if(Robot.claw.isClawVertical()) {
    		Robot.claw.setClawHorizontal();
    	} 
    	// Sets the wrist to vertical position if it is horizontal
    	else if(Robot.claw.isClawHorizontal()) {
    		Robot.claw.setClawVertical();
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
