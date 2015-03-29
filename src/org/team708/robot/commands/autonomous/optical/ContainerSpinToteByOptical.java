
package org.team708.robot.commands.autonomous.optical;

import org.team708.robot.AutoConstants;
import org.team708.robot.commands.autonomous.steps.DriveOpticalAndEncoder;
import org.team708.robot.commands.claw.CloseClaw;
import org.team708.robot.commands.claw.OpenClaw;
import org.team708.robot.commands.clawElevator.DecrementClawOne;
import org.team708.robot.commands.clawElevator.IncrementClawOne;
import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.team708.robot.commands.drivetrain.TurnToDegrees;
import org.team708.robot.commands.indexer.IndexerUp;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ContainerSpinToteByOptical extends CommandGroup {
	
//	private double irTolerance = 5;  //inches
        
    public  ContainerSpinToteByOptical() {
    	// Picks up one container
    	addSequential(new CloseClaw());
    	addSequential(new IncrementClawOne());
    	
    	// Drops the container on the tote
    	addSequential(new IncrementClawOne());
    	addSequential(new DriveStraightToEncoderDistance(AutoConstants.CONTAINER_TOTE_TO_TOTE_DISTANCE, AutoConstants.ENCODER_SPEED, false));
    	addSequential(new DecrementClawOne());
    	addParallel(new OpenClaw());
    	addSequential(new DecrementClawOne());
    	addSequential(new CloseClaw());
    	
    	// Go to auto zone
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, -AutoConstants.NINETY_DEGREE_TURN));
    	addSequential(new DriveOpticalAndEncoder(AutoConstants.TOTE_TO_AUTOZONE_DISTANCE, false));
    	
    	//Turn 90 degrees counterclockwise
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, AutoConstants.NINETY_DEGREE_TURN));
    }
}
