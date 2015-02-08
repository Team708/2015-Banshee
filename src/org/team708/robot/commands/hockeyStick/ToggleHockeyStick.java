package org.team708.robot.commands.hockeyStick;

import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleHockeyStick extends Command {

    public ToggleHockeyStick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.hockeyStick.getSolenoidValue().equals(Robot.hockeyStick.DEPLOYED)) {
    		Robot.hockeyStick.retractHockey();
    	} else {
    		Robot.hockeyStick.deployHockey();
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
