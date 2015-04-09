package org.team708.robot.commands.autonomous.encoder;

import edu.wpi.first.wpilibj.command.*;

import org.team708.robot.AutoConstants;
import org.team708.robot.commands.claw.CloseClaw;
import org.team708.robot.commands.claw.OpenClaw;
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
    	addSequential(new CloseClaw());
    	addSequential(new ClawElevatorByEncoder(11.0, true));
    	
    	// Gets the first tote
//    	addSequential(new DriveToIRDistance(Constants.IR_HAS_TOTE_DISTANCE, IR_TOLERANCE));
    	addParallel(new ClawElevatorByEncoder(7.5, true));
    	addSequential(new DriveStraightToEncoderDistance(AutoConstants.TOTE_DISTANCE_ONE, AutoConstants.ENCODER_SPEED));
    	addParallel(new IndexerUpAuto(AutoConstants.INDEXER_UP_DISTANCE, false));
    	addSequential(new WaitCommand(0.5));
    	
    	// Gets the second tote
    	addSequential(new DriveStraightToEncoderDistance(AutoConstants.TOTE_DISTANCE_SECOND, AutoConstants.ENCODER_SPEED, false));
//    	addSequential(new DriveToIRDistance(Constants.IR_HAS_TOTE_DISTANCE, IR_TOLERANCE));
    	addSequential(new ClawElevatorByEncoder(8.0, false));	// Was 5.0
//    	addParallel(new OpenClaw());
//    	addSequential(new ClawElevatorByEncoder(3.0, false));
//    	addSequential(new CloseClaw());
    	
    	// Go to auto zone
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, -AutoConstants.NINETY_DEGREE_TURN));
    	addSequential(new DriveStraightToEncoderDistance(AutoConstants.SWAGTONOMOUS_TO_AUTO_DISTANCE, AutoConstants.SWAGTONOMOUS_ENCODER_SPEED, false));
    	
    	//Turn 90 degrees counterclockwise
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, AutoConstants.NINETY_DEGREE_TURN));
    	
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
