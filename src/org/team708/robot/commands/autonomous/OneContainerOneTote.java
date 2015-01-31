package org.team708.robot.commands.autonomous;

import org.team708.robot.commands.drivetrain.TurnToDegrees;
import org.team708.robot.commands.visionProcessor.WaitUntilContainerClear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OneContainerOneTote extends CommandGroup {
    
    public  OneContainerOneTote() {
    	
    	addSequential(new WaitUntilContainerClear());
    	addSequential(new TurnToDegrees(0.6, -90));
    	
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
