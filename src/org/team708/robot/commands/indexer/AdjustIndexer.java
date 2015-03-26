package org.team708.robot.commands.indexer;

import org.team708.robot.Constants;
import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AdjustIndexer extends Command {

	private boolean directionUp;

    public AdjustIndexer(boolean directionUp) {
        // Use requires() here to declare subsystem dependencies
//    	requires(Robot.indexer);
    	
    	this.directionUp = directionUp;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.indexer.resetEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (directionUp) {
			Robot.indexer.raiseIndexer();
    	} else {
    		Robot.indexer.lowerIndexer();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (directionUp) {
    		return (Robot.indexer.getEncoderDistance() >= Constants.ADJUST_UP);
    	} else {
    		return (Robot.indexer.getEncoderDistance() <= -Constants.ADJUST_DOWN);
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.indexer.stopIndexer();
    	Robot.indexer.resetEncoder();
    	Robot.indexer.indexerDown = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}
