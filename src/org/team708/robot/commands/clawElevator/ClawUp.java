package org.team708.robot.commands.clawElevator;

import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClawUp extends Command {
	
	private boolean isAtLimit;

    public ClawUp() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.clawElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isAtLimit = Robot.clawElevator.getUpperSwitch();
    	
    	if (isAtLimit) {
    		cancel();
    	}
    	
    	Robot.clawElevator.resetEncoder();
    	Robot.clawElevator.incrementContainerHeight();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.clawElevator.moveUp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.clawElevator.getContainerHeight() == 4) {
    		return Robot.clawElevator.getUpperSwitch();
    	} else {
    		return Robot.clawElevator.getUpperSwitch() || Robot.clawElevator.isAtIntemediateStop(Robot.clawElevator.getContainerHeight() - 1);
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (Robot.clawElevator.getUpperSwitch()) {
    		Robot.clawElevator.setContainerHeight(4);
    	}
    	Robot.clawElevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
