
package org.team708.robot.commands.autonomous;

import org.team708.robot.Constants;
import org.team708.robot.commands.claw.ToggleClawOpen;
import org.team708.robot.commands.clawElevator.IncrementClawOne;
import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.team708.robot.commands.drivetrain.DriveToIRDistance;
import org.team708.robot.commands.drivetrain.TurnToDegrees;
import org.team708.robot.commands.indexer.IndexerDown;
import org.team708.robot.commands.indexer.IndexerUp;
import org.team708.robot.commands.intake.IntakeTote;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class OneContainerOneTote extends CommandGroup {
	
	private final double TURN_SPEED = 0.8;
	private final double TURN_ANGLE = 90.0;  //degrees
	private final double DRIVE_DISTANCE = 65.0; //inches
	private final double DRIVE_TO_TOTE = 16.0; //inches
	
	private double irTolerance = 5;  //inches
        
    public  OneContainerOneTote() {
    	

    	    addSequential(new ToggleClawOpen());
    	    addSequential(new WaitCommand(0.2));
    	    addSequential(new IncrementClawOne());
    	    addSequential(new WaitCommand(.2));
        	addSequential(new DriveStraightToEncoderDistance(DRIVE_TO_TOTE));  
    	
        	addSequential(new DriveToIRDistance(Constants.IR_HAS_TOTE_DISTANCE, irTolerance));
        	addSequential(new WaitCommand(0.1));
        	addSequential(new IndexerUp());
        	addSequential(new WaitCommand(0.1));
        	
        	addSequential(new TurnToDegrees(TURN_SPEED, TURN_ANGLE));
        	addSequential(new DriveStraightToEncoderDistance(DRIVE_DISTANCE));

        	//needs to stop and then drop tote
        	
//        	addSequential(new IndexerDown());        	
            	
 
    
    }
}
