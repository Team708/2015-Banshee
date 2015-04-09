package org.team708.robot.commands.clawElevator;

import org.team708.robot.Constants;
import org.team708.robot.Robot;
import org.team708.robot.util.Math708;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClawUp extends Command {
	
	private boolean isAtLimit;
	
	private boolean useSmoothing;

    public ClawUp() {
        this(false);
    }
    
    public ClawUp(boolean useSmoothing) {
    	// Use requires() here to declare subsystem dependencies
    	requires(Robot.clawElevator);
    	
    	this.useSmoothing = useSmoothing;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isAtLimit = Robot.clawElevator.getUpperSwitch();
    	
    	if (isAtLimit) {
    		cancel();
    	} else {
    		Robot.clawElevator.resetEncoder();
    	}
    	
//    	if (Robot.clawElevator.getContainerHeight() == 4) {
    		Robot.clawElevator.incrementContainerHeight();
//    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double moveValue = 1.0;
    	
    	if (useSmoothing) {
	    	if (Robot.clawElevator.getContainerHeight() == 4) {
	    		// TODO Something or other
	    	} else {
	        	double percentError = -Math708.getPercentError(Robot.clawElevator.getTravelDistance(), Constants.CLAW_ELEVATOR_UP_TRAVEL_DISTANCES[Robot.clawElevator.getContainerHeight() - 1]);
	    	
	        	if (percentError <= 0.5) {
	        		moveValue = Math708.makeWithin(percentError, Constants.CLAW_ELEVATOR_MOTOR_MINIMUM, moveValue);
	        	}
	    	}
    	}
    	
    	Robot.clawElevator.manualMove(moveValue);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.clawElevator.getContainerHeight() == 4) {
    		return Robot.clawElevator.getUpperSwitch();
    	} else {
    		return Robot.clawElevator.getUpperSwitch() || Robot.clawElevator.isAtIntemediateStop((Robot.clawElevator.getContainerHeight() - 1), true);
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (Robot.clawElevator.getUpperSwitch()) {
    		Robot.clawElevator.setContainerHeight(4);
    		Robot.clawElevator.resetEncoder();
    	}
    	Robot.clawElevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
