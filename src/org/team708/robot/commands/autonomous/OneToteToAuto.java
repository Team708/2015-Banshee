package org.team708.robot.commands.autonomous;

import org.team708.robot.Constants;
import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.team708.robot.commands.drivetrain.DriveToIRDistance;
import org.team708.robot.commands.drivetrain.TurnToDegrees;
import org.team708.robot.commands.indexer.IndexerUp;
import org.team708.robot.commands.indexer.IndexerDown;
//import org.team708.robot.commands.intake.IntakeTote;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class OneToteToAuto extends CommandGroup {
	
	private final double TURN_SPEED = 0.6;
	private final double TURN_ANGLE = 85.0;  //degrees
	private final double DRIVE_DISTANCE = 100.0; //inches
	
	private double MIN_VAL = 0.45;
	private double MAX_VAL = 0.55;
	private double IR_TOLERANCE = 4;  //inches
    
    public  OneToteToAuto() {
    	
//    	addSequential(new DriveToIRDistance(Constants.IR_HAS_TOTE_DISTANCE, MIN_VAL, MAX_VAL, IR_TOLERANCE));
//    	addSequential(new WaitCommand(0.2));
    	
//    	addSequential(new IntakeTote());
    	
    	addSequential(new IndexerUp());
    	addSequential(new WaitCommand(0.1));
    	addSequential(new TurnToDegrees(TURN_SPEED, TURN_ANGLE));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new DriveStraightToEncoderDistance(DRIVE_DISTANCE));

    	//needs to stop and then drop tote    	
    	addSequential(new IndexerDown());
    }
}
