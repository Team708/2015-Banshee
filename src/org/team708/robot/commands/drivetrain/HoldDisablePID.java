package org.team708.robot.commands.drivetrain;

import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HoldDisablePID extends Command {

    public HoldDisablePID() {
        // Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.setUsePID(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setUsePID(true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
