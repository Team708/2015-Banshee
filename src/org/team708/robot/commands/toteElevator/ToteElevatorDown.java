package org.team708.robot.commands.toteElevator;
import org.team708.robot.Robot;
import org.team708.robot.util.Math708;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToteElevatorDown extends Command {
	
	private final double threshold = 1;

    public ToteElevatorDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.toteElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!Robot.toteElevator.elevatorDown) {
    		Robot.toteElevator.lowerTote();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	return Math708.isWithinThreshold(Robot.toteElevator.getEncoderDistance(), -Robot.toteElevator.TOP_ENCODER_DISTANCE, threshold)
//    				|| Robot.toteElevator.elevatorDown;
    	return Robot.toteElevator.getEncoderDistance() <= Robot.toteElevator.BOTTOM_ENCODER_DISTANCE;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.toteElevator.stopTote();
    	Robot.toteElevator.elevatorDown = true;
    	Robot.toteElevator.setToteCount(0);
    	Robot.toteElevator.resetEncoder();
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}
