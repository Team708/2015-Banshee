package org.team708.robot.commands.clawElevator;

import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveUpOffSwitch extends Command {
	
	private boolean atUpperLimit;
	private boolean atLowerLimit;

    public MoveUpOffSwitch() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.clawElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	atUpperLimit = Robot.clawElevator.getUpperSwitch();
    	atLowerLimit = Robot.clawElevator.getLowerSwitch();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!atUpperLimit) {
    		Robot.clawElevator.moveUp();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (atLowerLimit) {
    		return !Robot.clawElevator.getLowerSwitch();
    	} else {
    		return !Robot.clawElevator.getSeries();
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.clawElevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
