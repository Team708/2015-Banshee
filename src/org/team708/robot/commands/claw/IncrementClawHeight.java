package org.team708.robot.commands.claw;

import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IncrementClawHeight extends Command {

    private int target;
	private boolean isOnLimit;
    
    
	public IncrementClawHeight(int levels) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.clawElevator);
    	target = Robot.clawElevator.getContainerHeight() + levels;
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	if(Robot.clawElevator.getSeries()) {
    		isOnLimit = true;
    	} else {
    		isOnLimit = false;
    	}
    	
    	if(Robot.clawElevator.getUpperSwitch()) {
    		cancel();	// You're already at the limit quit while you're ahead
    	} else if(target >= Robot.clawElevator.UPPER_LIMIT) {
    		target = Robot.clawElevator.UPPER_LIMIT;	// You may have a target that shoots past the limit, so make sure you don't
    	} else {
    		// Do nothing; the target is appropriate
    	}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(isOnLimit) {
    		if(!Robot.clawElevator.getSeries()) {
    			isOnLimit = false;
    		}
    	} else {
    		if(Robot.clawElevator.getSeries()) {
    			isOnLimit = true;
    			Robot.clawElevator.incrementContainerHeight();
    		}
    	}
    	
    	if(Robot.clawElevator.getUpperSwitch()) {
    		Robot.clawElevator.stop();
    		Robot.clawElevator.setContainerHeightMax();
    	} else {
    		Robot.clawElevator.moveUp();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.clawElevator.getContainerHeight() == target);
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
