package org.team708.robot.commands;

import org.team708.robot.Robot;
import org.team708.robot.util.Math708;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToteElevatorUp extends Command {
	
	private final double threshold = 1;
	private boolean atToteLimitMax;

    public ToteElevatorUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.toteElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	atToteLimitMax = (Robot.toteElevator.toteCount == Robot.toteElevator.TOTE_UPPER_LIMIT);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		if (!atToteLimitMax) {
			Robot.toteElevator.raiseTote();
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math708.isWithinThreshold(Robot.toteElevator.getEncoderDistance(), Robot.toteElevator.TOP_ENCODER_DISTANCE, threshold)
    			|| atToteLimitMax;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.toteElevator.stopTote();
    	Robot.toteElevator.setToteCount(Robot.toteElevator.getToteCount() + 1);
    	Robot.toteElevator.resetEncoder();
    	Robot.toteElevator.elevatorDown = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}
