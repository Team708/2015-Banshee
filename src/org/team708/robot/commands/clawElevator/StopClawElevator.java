package org.team708.robot.commands.clawElevator;

import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopClawElevator extends Command {

    public StopClawElevator() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.clawElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.clawElevator.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.clawElevator.stop();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.clawElevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.clawElevator.stop();
    }
}
