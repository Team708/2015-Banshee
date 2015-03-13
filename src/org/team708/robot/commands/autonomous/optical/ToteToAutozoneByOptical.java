package org.team708.robot.commands.autonomous.optical;

import org.team708.robot.AutoConstants;
import org.team708.robot.commands.autonomous.steps.DriveOpticalAndEncoder;
import org.team708.robot.commands.claw.CloseClaw;
import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
//import org.team708.robot.commands.drivetrain.DriveToIRDistance;
import org.team708.robot.commands.drivetrain.TurnToDegrees;
import org.team708.robot.commands.indexer.IndexerUp;
import org.team708.robot.commands.indexer.IndexerDown;




//import org.team708.robot.commands.intake.IntakeTote;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ToteToAutozoneByOptical extends CommandGroup {
		
//	private double MIN_VAL = 0.45;
//	private double MAX_VAL = 0.55;
//	private double IR_TOLERANCE = 4;  //inches
    
    public  ToteToAutozoneByOptical() {
    	
//    	addSequential(new DriveToIRDistance(Constants.IR_HAS_TOTE_DISTANCE, MIN_VAL, MAX_VAL, IR_TOLERANCE));
//    	addSequential(new WaitCommand(0.2));
    	
//    	addSequential(new IntakeTote());
    	
    	addParallel(new CloseClaw());
    	addSequential(new IndexerUp());
    	addSequential(new WaitCommand(0.1));
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, AutoConstants.NINETY_DEGREE_TURN));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new DriveOpticalAndEncoder(AutoConstants.TOTE_TO_AUTOZONE_DISTANCE));
    	
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, -AutoConstants.NINETY_DEGREE_TURN));
    	//needs to stop and then drop tote    	
//    	addSequential(new IndexerDown());
    }
}
