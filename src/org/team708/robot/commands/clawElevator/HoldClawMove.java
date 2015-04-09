package org.team708.robot.commands.clawElevator;

import org.team708.robot.OI;
import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HoldClawMove extends Command {
	
	private boolean moveUp;

    public HoldClawMove(boolean moveUp) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.clawElevator);
        
        this.moveUp = moveUp;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (moveUp) {
    		if (Robot.clawElevator.getUpperSwitch()) {
    			cancel();
    		} else {
    			Robot.clawElevator.moveUp();
    		}
    	} else {
    		if (Robot.clawElevator.getLowerSwitch()) {
    			cancel();
    		} else {
    			Robot.clawElevator.moveDown();
    		}
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (moveUp) {
    		return !OI.clawHeightIncrement.get() || Robot.clawElevator.getUpperSwitch();
    	} else {
    		return !OI.clawHeightDecrement.get() || Robot.clawElevator.getLowerSwitch();
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
