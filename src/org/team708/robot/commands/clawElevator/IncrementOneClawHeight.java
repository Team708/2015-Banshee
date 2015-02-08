package org.team708.robot.commands.clawElevator;

import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Increases the height of the container elevator by a specified integer increment
 */
public class IncrementOneClawHeight extends Command {

	private boolean atUpperSwitch;
    
	public IncrementOneClawHeight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.clawElevator);
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	atUpperSwitch = Robot.clawElevator.getUpperSwitch();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!atUpperSwitch) {
    		Robot.clawElevator.moveUp();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// Returns true if containerHeight is equal to the target height
    	return Robot.clawElevator.getSeries() || Robot.clawElevator.getUpperSwitch();
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
