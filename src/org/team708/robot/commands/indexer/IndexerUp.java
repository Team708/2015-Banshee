package org.team708.robot.commands.indexer;

import org.team708.robot.Constants;
import org.team708.robot.OI;
import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IndexerUp extends Command {

	private boolean atToteLimitMax;
	private boolean hasToteInitially;
	
	private boolean irHasTote = false;
	
	private boolean useIR;

    public IndexerUp() {
        // Use requires() here to declare subsystem dependencies
//    	requires(Robot.indexer);
    	useIR = true;
    }
    
    public IndexerUp(boolean useIR) {
    	this();
    	this.useIR = useIR;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.indexer.resetEncoder();
    	atToteLimitMax = (Robot.indexer.toteCount == Constants.TOTE_LIMIT);
    	if (useIR) {
//    		hasToteInitially = (Robot.drivetrain.getIRDistance() <= Constants.IR_HAS_TOTE_DISTANCE);
    	} else {
    		hasToteInitially = true;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!OI.indexerManualOverride.get()) {
			if (Robot.indexer.toteCount == 0 && !irHasTote) {
				irHasTote = (Robot.indexer.getIRDistance() <= Constants.IR_HAS_TOTE_DISTANCE);
			}
			
		//		if (!atToteLimitMax && hasToteInitially) {
				Robot.indexer.raiseIndexer();
		//		}
    	} else {
    		cancel();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
 //   	return Robot.indexer.getEncoderDistance() >= Constants.TOTE_HEIGHT || !hasToteInitially;
    	if (Robot.indexer.toteCount>=1)
    		return (Robot.indexer.getEncoderDistance() >= Constants.TOTE_HEIGHT) || !hasToteInitially || OI.indexerManualOverride.get();
    	else
    	    return (irHasTote && (Robot.indexer.getIRDistance() > Constants.IR_HAS_TOTE_DISTANCE)) || !hasToteInitially || OI.indexerManualOverride.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.indexer.stopIndexer();
    	Robot.indexer.setToteCount(Robot.indexer.getToteCount() + 1);
    	Robot.indexer.indexerDown = false;
    	irHasTote = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}
