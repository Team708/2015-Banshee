package org.team708.robot.commands.autonomous.encoder;

import org.team708.robot.AutoConstants;
import org.team708.robot.commands.claw.CloseClaw;
import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.team708.robot.commands.drivetrain.TurnToDegrees;
import org.team708.robot.commands.indexer.IndexerUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ThreeTotes extends CommandGroup {
    
    public  ThreeTotes() {
    	addParallel(new CloseClaw());
    	//pick up first tote and move to second
    	addSequential(new IndexerUp());
    	
    	//pick up second tote and move to third
    	addSequential(new IndexerUp());
    	
    	//pick up third tote and move to auto
    	addSequential(new IndexerUp());
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, AutoConstants.NINETY_DEGREE_TURN));
    	addSequential(new DriveStraightToEncoderDistance(AutoConstants.TOTE_TO_AUTOZONE_DISTANCE, AutoConstants.ENCODER_SPEED));
    	
    	//turn 90 degrees counterclockwise
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
