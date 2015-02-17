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
	
	private static final double TURN_SPEED = 0.6;
	private static final double ENCODER_SPEED = 0.45;
	private static final double IR_TOLERANCE = 0.0625;
	
	private static final double TO_FIRST_TOTE_DISTANCE = 15.0;
	private static final double TURN_DEGREES_TO_SECOND_TOTE = 169.0;
	private static final double TO_SECOND_TOTE_DISTANCE = 45.0;
	
	private static final double TURN_DEGREES_TO_AUTO_ZONE = -85.0;
	private static final double TO_AUTO_ZONE_DISTANCE = 115.0;
    
    public  TwoToteOneContainer() {
    	
    	// Picks up one container
    	addSequential(new IncrementClawOne());
    	
    	// Gets the first tote
//    	addSequential(new DriveToIRDistance(Constants.IR_HAS_TOTE_DISTANCE, IR_TOLERANCE));
    	addSequential(new DriveStraightToEncoderDistance(TO_FIRST_TOTE_DISTANCE, ENCODER_SPEED));
    	addSequential(new IndexerUp(false));
    	
    	// Gets the second tote
    	addSequential(new TurnToDegrees(TURN_SPEED, TURN_DEGREES_TO_SECOND_TOTE));
    	addSequential(new DriveStraightToEncoderDistance(TO_SECOND_TOTE_DISTANCE, ENCODER_SPEED));
//    	addSequential(new DriveToIRDistance(Constants.IR_HAS_TOTE_DISTANCE, IR_TOLERANCE));
    	addSequential(new IndexerUp(false));
    	
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
