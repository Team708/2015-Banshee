package org.team708.robot.commands.autonomous;

import org.team708.robot.Constants;
import org.team708.robot.commands.claw.ToggleClawOpen;
import org.team708.robot.commands.clawElevator.IncrementClawOne;
import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.team708.robot.commands.drivetrain.DriveToIRDistance;
import org.team708.robot.commands.drivetrain.TurnToDegrees;
import org.team708.robot.commands.indexer.IndexerUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * An autonomous mode where the robot starts at a container,
 * grabs it, and then goes for the two totes on either side.
 */
public class TwoToteOneContainer extends CommandGroup {
	
	private static final double FORWARD_ENCODER_DISTANCE = -27.5;
	
	private static final double TURN_DEGREES_TO_FIRST_TOTE = 90.0;
	private static final double TURN_SPEED = 0.75;
	private static final double IR_TOLERANCE = 0.0625;
	
	private static final double TURN_DEGREES_TO_SECOND_TOTE = 180.0;
	private static final double TO_SECOND_TOTE_DISTANCE = 42.0;
	
	private static final double TURN_DEGREES_TO_AUTO_ZONE = 90.0;
	private static final double TO_AUTO_ZONE_DISTANCE = 42.0;
    
    public  TwoToteOneContainer() {
    	
    	// Grabs and picks up one container
    	addSequential(new ToggleClawOpen());
    	addSequential(new IncrementClawOne());
    	
    	// Gets the first tote
    	addSequential(new DriveStraightToEncoderDistance(FORWARD_ENCODER_DISTANCE));
    	addSequential(new TurnToDegrees(TURN_SPEED, TURN_DEGREES_TO_FIRST_TOTE));
    	addSequential(new DriveToIRDistance(Constants.IR_HAS_TOTE_DISTANCE, IR_TOLERANCE));
    	addSequential(new IndexerUp());
    	
    	// Gets the second tote
    	addSequential(new TurnToDegrees(TURN_SPEED, TURN_DEGREES_TO_SECOND_TOTE));
    	addSequential(new DriveStraightToEncoderDistance(TO_SECOND_TOTE_DISTANCE));
    	addSequential(new DriveToIRDistance(Constants.IR_HAS_TOTE_DISTANCE, IR_TOLERANCE));
    	addSequential(new IndexerUp());
    	
    	// Go to auto zone
    	addSequential(new TurnToDegrees(TURN_SPEED, TURN_DEGREES_TO_AUTO_ZONE));
    	addSequential(new DriveStraightToEncoderDistance(TO_AUTO_ZONE_DISTANCE));
    	
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
