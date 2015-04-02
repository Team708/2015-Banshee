package org.team708.robot.commands.clawElevator;

import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClawDown extends Command {
	
	private boolean isAtLimit;

    public ClawDown() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.clawElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isAtLimit = Robot.clawElevator.getLowerSwitch();
    	
    	if (isAtLimit) {
    		cancel();
    	}
    	
    	Robot.clawElevator.resetEncoder();
    	Robot.clawElevator.decrementContainerHeight();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.clawElevator.moveDown();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.clawElevator.getContainerHeight() == 0) {
    		return Robot.clawElevator.getLowerSwitch();
    	} else {
    		return Robot.clawElevator.getLowerSwitch() || Robot.clawElevator.isAtIntemediateStop(Math.abs(Robot.clawElevator.getContainerHeight() - 3));
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (Robot.clawElevator.getLowerSwitch()) {
    		Robot.clawElevator.setContainerHeight(0);
    	}
    	Robot.clawElevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
