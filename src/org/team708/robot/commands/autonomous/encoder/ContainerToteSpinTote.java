package org.team708.robot.commands.autonomous.encoder;

import edu.wpi.first.wpilibj.command.*;

import org.team708.robot.AutoConstants;
import org.team708.robot.commands.clawElevator.*;
import org.team708.robot.commands.drivetrain.*;
import org.team708.robot.commands.indexer.*;

/**
 * An autonomous mode where the robot starts at a container,
 * grabs it, and then goes for the two totes on either side.
 */
public class ContainerToteSpinTote extends CommandGroup {
	
//	private static final double IR_TOLERANCE = 0.0625;
    
    public  ContainerToteSpinTote() {
    	
    	// Picks up one container
    	addSequential(new IncrementClawOne());
    	
    	// Gets the first tote
//    	addSequential(new DriveToIRDistance(Constants.IR_HAS_TOTE_DISTANCE, IR_TOLERANCE));
    	addSequential(new DriveStraightToEncoderDistance(AutoConstants.TOTE_DISTANCE_ONE, AutoConstants.ENCODER_SPEED));
    	addSequential(new IndexerUpAuto(AutoConstants.INDEXER_UP_DISTANCE, false));
    	
    	// Gets the second tote
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, AutoConstants.TOTE_TWO_TURN_ANGLE));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new DriveStraightToEncoderDistance(AutoConstants.TOTE_DISTANCE_SECOND, AutoConstants.ENCODER_SPEED));
//    	addSequential(new DriveToIRDistance(Constants.IR_HAS_TOTE_DISTANCE, IR_TOLERANCE));
    	addSequential(new IndexerUpAuto(AutoConstants.INDEXER_UP_DISTANCE, false));
    	
    	// Go to auto zone
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, -AutoConstants.NINETY_DEGREE_TURN));
    	addSequential(new DriveStraightToEncoderDistance(AutoConstants.TOTE_TO_AUTOZONE_DISTANCE));
    	
    	//Turn 90 degrees counterclockwise
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, -AutoConstants.NINETY_DEGREE_TURN));
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
