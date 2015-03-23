package org.team708.robot.commands.autonomous.optical;

import org.team708.robot.AutoConstants;
import org.team708.robot.commands.autonomous.steps.DriveByOpticalToAuto;
import org.team708.robot.commands.autonomous.steps.DriveOpticalAndEncoder;
import org.team708.robot.commands.claw.CloseClaw;
import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.team708.robot.commands.drivetrain.TurnToDegrees;
import org.team708.robot.commands.indexer.IndexerUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ThreeTotesByOptical extends CommandGroup {
    
    public  ThreeTotesByOptical() {
    	addParallel(new CloseClaw());
    	//pick up first tote and move to second
    	addParallel(new IndexerUp());
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, -AutoConstants.THREE_TOTE_SMACK_ANGLE));
    	addSequential(new DriveStraightToEncoderDistance(AutoConstants.THREE_TOTE_CONTAINER_DISTANCE, AutoConstants.ENCODER_SPEED));
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, AutoConstants.THREE_TOTE_SMACK_ANGLE));
    	addSequential(new DriveStraightToEncoderDistance(AutoConstants.THREE_TOTE_TOTE_DISTANCE));
    	
    	//pick up second tote and move to third
    	addParallel(new IndexerUp());
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, -AutoConstants.THREE_TOTE_SMACK_ANGLE));
    	addSequential(new DriveStraightToEncoderDistance(AutoConstants.THREE_TOTE_CONTAINER_DISTANCE, AutoConstants.ENCODER_SPEED));
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, AutoConstants.THREE_TOTE_SMACK_ANGLE));
    	addSequential(new DriveStraightToEncoderDistance(AutoConstants.THREE_TOTE_TOTE_DISTANCE));
    	
    	//pick up third tote and move to auto
    	addParallel(new IndexerUp());
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, AutoConstants.NINETY_DEGREE_TURN));
    	addSequential(new DriveOpticalAndEncoder(AutoConstants.TOTE_TO_AUTOZONE_DISTANCE));
    	
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
