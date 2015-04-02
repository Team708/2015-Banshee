package org.team708.robot.commands.autonomous.encoder;

import org.team708.robot.AutoConstants;
import org.team708.robot.commands.claw.CloseClaw;
import org.team708.robot.commands.clawElevator.ClawUp;
import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
//import org.team708.robot.commands.drivetrain.DriveToIRDistance;
import org.team708.robot.commands.drivetrain.TurnToDegrees;
import org.team708.robot.commands.indexer.IndexerUp;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;


public class ContainerToteTote extends CommandGroup {
		
//	private double irTolerance = 5;  //inches (was .0625)?
        	
    public  ContainerToteTote() {
    	
    	addSequential(new CloseClaw());
	    addSequential(new ClawUp());
	    addSequential(new WaitCommand(0.2));
	    
    	addSequential(new DriveStraightToEncoderDistance(AutoConstants.CONTAINER_TOTE_TOTE_FIRST, AutoConstants.ENCODER_SPEED));
//    	addSequential(new WaitCommand(0.1));
//    	addSequential(new DriveToIRDistance(Constants.IR_HAS_TOTE_DISTANCE, irTolerance));
//    	addSequential(new WaitCommand(0.1));
    	addSequential(new IndexerUp(false));
//
    	addSequential(new DriveStraightToEncoderDistance(AutoConstants.CONTAINER_TOTE_TOTE_SECOND, AutoConstants.ENCODER_SPEED));  
//    	addSequential(new DriveToIRDistance(Constants.IR_HAS_TOTE_DISTANCE, irTolerance));
//    	addSequential(new WaitCommand(0.1));
    	addSequential(new IndexerUp(false));
    	
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, AutoConstants.NINETY_DEGREE_TURN));
    	addSequential(new DriveStraightToEncoderDistance(AutoConstants.TOTE_TO_AUTOZONE_DISTANCE));
    	
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, -AutoConstants.NINETY_DEGREE_TURN));
//    	
////need to drop the tote for it to count
//    	addSequential(new TurnToDegrees(TURN_SPEED, TURN_ANGLE));
//    	addSequential(new IndexerDown());
    }
}
