package org.team708.robot.commands.clawElevator;

import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Decreases the height of the container elevator by a specified integer decrement
 */
public class DecrementOneClawHeight extends Command {

private boolean atLowerSwitch;
    
	public DecrementOneClawHeight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.clawElevator);
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	atLowerSwitch = Robot.clawElevator.getLowerSwitch();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!atLowerSwitch) {
    		Robot.clawElevator.moveDown();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// Returns true if containerHeight is equal to the target height
    	return Robot.clawElevator.getSeries() || Robot.clawElevator.getLowerSwitch();
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
