package org.team708.robot.commands.autonomous;

import org.team708.robot.Constants;
import org.team708.robot.commands.claw.ToggleClawOpen;
import org.team708.robot.commands.clawElevator.IncrementClawOne;
import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.team708.robot.commands.drivetrain.DriveToIRDistance;
import org.team708.robot.commands.drivetrain.TurnToDegrees;
import org.team708.robot.commands.indexer.IndexerUp;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class GetContainerGotoAutozone extends CommandGroup {
    
	private final double TURN_SPEED = 0.8;
	private final double TURN_ANGLE = 90.0;  //degrees
	private final double DRIVE_DISTANCE = 55.0; //inches
	
    public  GetContainerGotoAutozone() {
        // Add Commands here:
    	
    	addSequential(new ToggleClawOpen());
    	addSequential(new WaitCommand(0.2));
    	addSequential(new IncrementClawOne());
    	addSequential(new WaitCommand(.2));
    	addSequential(new DriveStraightToEncoderDistance(DRIVE_DISTANCE));   
//    	addSequential(new DecrementClawOne());
//    	addSequential(new ToggleClawOpen());

    }
}
