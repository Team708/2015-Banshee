package org.team708.robot.commands.claw;

import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Decreases the height of the container elevator by a specified integer decrement
 */
public class DecrementClawHeight extends Command {

	private int target;
	private boolean isInSwitchSeries;
	
    public DecrementClawHeight(int levels) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.clawElevator);
    	target = Robot.clawElevator.getContainerHeight() - levels;	// Sets the target containerHeight value
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	// Checks to see if you are starting on the series
    	if(Robot.clawElevator.getSeries()) {
    		isInSwitchSeries = true;
    	} else {
    		isInSwitchSeries = false;
    	}
    	
    	// Checks to see if you are at the lower limit. If you are, stop the command immediately
    	if(Robot.clawElevator.getLowerSwitch()) {
    		cancel();	// You're already at the limit; quit while you're ahead
    	} else if(target <= Robot.clawElevator.LOWER_LIMIT) {
    		target = Robot.clawElevator.LOWER_LIMIT;	// You may have a target that shoots past the limit, so make sure you don't
    	} else {
    		// Do nothing; the target is appropriate
    	}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/* 
    	 * If you are on the series, wait to see if this changes
    	 * If it does change, indicate you are no longer on the series
    	 */
    	if(isInSwitchSeries) {
    		if(!Robot.clawElevator.getSeries()) {
    			isInSwitchSeries = false;
    		}
    	} 
    	/* 
    	 * If you are not on the series, wait to see if this changes,
    	 * If it does change, indicate you are now on the series and decrement containerHeight
    	 */
    	else {
    		if(Robot.clawElevator.getSeries()) {
    			isInSwitchSeries = true;
    			Robot.clawElevator.decrementContainerHeight();
    		}
    	}
    	 
    	// If you are on the lower switch, stop immediately, set the container height to minimum, and cancel the command
    	if(Robot.clawElevator.getLowerSwitch()) {
    		Robot.clawElevator.stop();
    		Robot.clawElevator.setContainerHeightMin();
    		cancel();
    	} 
    	// If you are not on the lower limit, move down
    	else {
    		Robot.clawElevator.moveDown();
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// Returns true if containerHeight is equal to the target height
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
