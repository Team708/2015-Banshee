package org.team708.robot.commands.autonomous;

import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.team708.robot.commands.drivetrain.TurnToDegrees;
import org.team708.robot.commands.indexer.IndexerUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ThreeTotes extends CommandGroup {
	
	private final double TURN_SPEED = 0.8;
	private final double TURN_ANGLE = 90.0;
	private final double ENCODER_SPEED = 0.45;
	
	private final double DRIVE_DISTANCE = 36.0;
	private static final double TO_AUTO_ZONE_DISTANCE = 84.0;
    
    public  ThreeTotes() {
    	//pick up first tote and move to second
    	addSequential(new IndexerUp());
    	addSequential(new DriveStraightToEncoderDistance(DRIVE_DISTANCE, ENCODER_SPEED));
    	
    	//pick up second tote and move to third
    	addSequential(new IndexerUp());
    	addSequential(new DriveStraightToEncoderDistance(DRIVE_DISTANCE, ENCODER_SPEED));
    	
    	//pick up third tote and move to auto
    	addSequential(new IndexerUp());
    	addSequential(new TurnToDegrees(TURN_SPEED, TURN_ANGLE));
    	addSequential(new DriveStraightToEncoderDistance(TO_AUTO_ZONE_DISTANCE, ENCODER_SPEED));
    	
    	//turn 90 degrees counterclockwise
    	addSequential(new TurnToDegrees(TURN_SPEED, -TURN_ANGLE));
    	
    	
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
