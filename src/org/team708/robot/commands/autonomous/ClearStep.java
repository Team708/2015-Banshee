package org.team708.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.team708.robot.commands.hockeyStick.*;
import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.team708.robot.commands.drivetrain.TurnToDegrees;

/**
 *
 */
public class ClearStep extends CommandGroup {
    
    public  ClearStep() {
       
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
    	
    	addSequential(new DeployHockeyStick());
    	addSequential(new DriveStraightToEncoderDistance(25));
    	addSequential(new CloseHockeyStick());
    	addSequential(new TurnToDegrees(1.0, 90.0));
    	addSequential(new DriveStraightToEncoderDistance(5));
    	
    }
}
