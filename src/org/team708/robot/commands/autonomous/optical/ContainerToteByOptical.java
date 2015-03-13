
package org.team708.robot.commands.autonomous.optical;

import org.team708.robot.AutoConstants;
import org.team708.robot.commands.autonomous.steps.DriveOpticalAndEncoder;
import org.team708.robot.commands.clawElevator.IncrementClawOne;
import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.team708.robot.commands.drivetrain.TurnToDegrees;
import org.team708.robot.commands.indexer.IndexerUp;
import org.team708.robot.commands.intake.IntakeByTime;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ContainerToteByOptical extends CommandGroup {
	
//	private double irTolerance = 5;  //inches
        
    public  ContainerToteByOptical() {
    	// Picks up one container
    	addSequential(new IncrementClawOne());
    	
    	// Gets the  tote
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, AutoConstants.CONTAINER_TOTE_TURN_ANGLE));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new DriveStraightToEncoderDistance(AutoConstants.CONTAINER_TOTE_TO_TOTE_DISTANCE, AutoConstants.ENCODER_SPEED));
    	addParallel(new IntakeByTime(12));
//    	addSequential(new DriveToIRDistance(Constants.IR_HAS_TOTE_DISTANCE, IR_TOLERANCE));
    	addSequential(new IndexerUp(false));
    	
    	// Go to auto zone
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, -AutoConstants.NINETY_DEGREE_TURN));
    	addSequential(new DriveOpticalAndEncoder(AutoConstants.TOTE_TO_AUTOZONE_DISTANCE));
    	
    	//Turn 90 degrees counterclockwise
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, -AutoConstants.NINETY_DEGREE_TURN));
    }
}
