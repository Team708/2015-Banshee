package org.team708.robot.commands.autonomous;

import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.team708.robot.commands.drivetrain.TurnToDegrees;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveToAutozone extends CommandGroup {
	
	private final double DISTANCE = 52.0; // in inches 		Was 40
	private final double TURN_SPEED     = 0.6;
	private final double FINAL_TURN_ANGLE = -90.0; //degrees
	
    public  MoveToAutozone() {
    	
    	addSequential(new DriveStraightToEncoderDistance(DISTANCE));
    	
    	addSequential(new TurnToDegrees(TURN_SPEED, FINAL_TURN_ANGLE));
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
