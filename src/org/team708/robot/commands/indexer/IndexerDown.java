package org.team708.robot.commands.indexer;

import org.team708.robot.Constants;
import org.team708.robot.OI;
import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IndexerDown extends Command {
	
	private final double EXTRA_TRAVEL_DISTANCE = 5;

    public IndexerDown() {
        // Use requires() here to declare subsystem dependencies
//    	requires(Robot.indexer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.indexer.resetEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!OI.indexerManualOverride.get()) {
	//    	if (!Robot.indexer.indexerDown) {
	    		Robot.indexer.lowerIndexer();
	//    	}
    	} else {
    		cancel();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	return Math708.isWithinThreshold(Robot.toteElevator.getEncoderDistance(), -Robot.toteElevator.TOP_ENCODER_DISTANCE, threshold)
//    				|| Robot.toteElevator.elevatorDown;
    	return Robot.indexer.getEncoderDistance() <= -(Constants.TOTE_HEIGHT + EXTRA_TRAVEL_DISTANCE) || OI.indexerManualOverride.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.indexer.stopIndexer();
    	Robot.indexer.indexerDown = true;
    	Robot.indexer.setToteCount(0);
    	Robot.indexer.resetEncoder();
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}
