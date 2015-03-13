package org.team708.robot.commands.autonomous;

import org.team708.robot.AutoConstants;
import org.team708.robot.commands.autonomous.steps.DriveOpticalAndEncoder;
import org.team708.robot.commands.clawElevator.IncrementClawOne;
import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.team708.robot.commands.drivetrain.TurnToDegrees;
import org.team708.robot.commands.intake.IntakeByTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShoveAll extends CommandGroup {
	
	private double FIELD_WIDTH = 310;
    
    public  ShoveAll() {
    	
    	addParallel(new IntakeByTime(5.0));
    	addSequential(new IncrementClawOne());
    	
    	addSequential(new DriveStraightToEncoderDistance(FIELD_WIDTH));
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, AutoConstants.NINETY_DEGREE_TURN));
    	
    	addSequential(new DriveOpticalAndEncoder(AutoConstants.TOTE_TO_AUTOZONE_DISTANCE));
    	
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
