
package org.team708.robot.commands.autonomous;

import org.team708.robot.commands.clawElevator.IncrementClawOne;
import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.team708.robot.commands.drivetrain.TurnToDegrees;
import org.team708.robot.commands.indexer.IndexerUp;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class OneContainerOneTote extends CommandGroup {
	
	private final double TURN_SPEED     = 0.6;
	private final double TURN_TO_TOTE_ANGLE     = 167.0;  //degrees
	private final double TURN_TO_AUTO_ZONE_ANGLE = -85.0;
	
	private final double TO_TOTE_DISTANCE = 38.0;
	private final double ENCODER_SPEED = 0.45;
	
	private final double TO_AUTO_ZONE_DISTANCE = 84.0;  //inches
	
//	private double irTolerance = 5;  //inches
        
    public  OneContainerOneTote() {
    	// Picks up one container
    	addSequential(new IncrementClawOne());
    	
    	// Gets the  tote
    	addSequential(new TurnToDegrees(TURN_SPEED, TURN_TO_TOTE_ANGLE));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new DriveStraightToEncoderDistance(TO_TOTE_DISTANCE, ENCODER_SPEED));
//    	addSequential(new DriveToIRDistance(Constants.IR_HAS_TOTE_DISTANCE, IR_TOLERANCE));
    	addSequential(new IndexerUp(false));
    	
    	// Go to auto zone
    	addSequential(new TurnToDegrees(TURN_SPEED, TURN_TO_AUTO_ZONE_ANGLE));
    	addSequential(new DriveStraightToEncoderDistance(TO_AUTO_ZONE_DISTANCE, ENCODER_SPEED));
    }
}
