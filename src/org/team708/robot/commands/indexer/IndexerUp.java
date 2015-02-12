package org.team708.robot.commands.indexer;

import org.team708.robot.Constants;
import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IndexerUp extends Command {

	private boolean atToteLimitMax;
	private boolean hasToteInitially;

    public IndexerUp() {
        // Use requires() here to declare subsystem dependencies
//    	requires(Robot.indexer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.indexer.resetEncoder();
    	atToteLimitMax = (Robot.indexer.toteCount == Constants.TOTE_LIMIT);
    	hasToteInitially = (Robot.drivetrain.getIRDistance() <= Constants.IR_HAS_TOTE_DISTANCE);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		if (!atToteLimitMax && hasToteInitially) {
			Robot.indexer.raiseIndexer();
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.indexer.getEncoderDistance() >= Constants.TOTE_HEIGHT || !hasToteInitially;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.indexer.stopIndexer();
    	Robot.indexer.setToteCount(Robot.indexer.getToteCount() + 1);
    	Robot.indexer.resetEncoder();
    	Robot.indexer.indexerDown = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}
