package org.team708.robot.commands;

import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToteElevatorUp extends Command {

    public ToteElevatorUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.toteElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.toteElevator.YToteUp();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.toteElevator.MOVE_COMPLETED;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
