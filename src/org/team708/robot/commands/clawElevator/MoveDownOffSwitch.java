package org.team708.robot.commands.clawElevator;

import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveDownOffSwitch extends Command {
	
	private boolean atLowerLimit;
	private boolean atUpperLimit;

    public MoveDownOffSwitch() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.clawElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	atLowerLimit = Robot.clawElevator.getLowerSwitch();
    	atUpperLimit = Robot.clawElevator.getUpperSwitch();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!atLowerLimit) {
    		Robot.clawElevator.moveDown();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (atUpperLimit) {
    		return !Robot.clawElevator.getUpperSwitch();
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
    }
}
