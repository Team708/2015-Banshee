
package org.team708.robot.commands.autonomous.optical;

import org.team708.robot.AutoConstants;
import org.team708.robot.commands.autonomous.steps.DriveOpticalAndEncoder;
import org.team708.robot.commands.claw.CloseClaw;
import org.team708.robot.commands.claw.OpenClaw;
import org.team708.robot.commands.clawElevator.ClawDown;
import org.team708.robot.commands.clawElevator.ClawUp;
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
    	addSequential(new ClawUp());
    	
    	// Drops the container on the tote
    	addSequential(new ClawUp());
    	addSequential(new DriveStraightToEncoderDistance(AutoConstants.CONTAINER_TOTE_TO_TOTE_DISTANCE, AutoConstants.ENCODER_SPEED, false));
    	addSequential(new ClawDown());
    	addParallel(new OpenClaw());
    	addSequential(new ClawDown());
    	addSequential(new CloseClaw());
    	
    	// Go to auto zone
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, -AutoConstants.NINETY_DEGREE_TURN));
    	addSequential(new DriveOpticalAndEncoder(AutoConstants.TOTE_TO_AUTOZONE_DISTANCE, false));
    	
    	//Turn 90 degrees counterclockwise
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, AutoConstants.NINETY_DEGREE_TURN));
    }
}
