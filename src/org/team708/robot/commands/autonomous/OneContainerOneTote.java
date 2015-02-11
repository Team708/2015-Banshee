
package org.team708.robot.commands.autonomous;

import org.team708.robot.Constants;
import org.team708.robot.commands.drivetrain.DriveToIRDistance;
import org.team708.robot.commands.indexer.IndexerUp;
import org.team708.robot.commands.intake.IntakeTote;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OneContainerOneTote extends CommandGroup {
	
	private double irTolerance = 1;
    
    public  OneContainerOneTote() {
    	
    	addSequential(new DriveToIRDistance(Constants.IR_HAS_TOTE_DISTANCE, irTolerance));
    	addSequential(new IntakeTote());
    	addSequential(new IndexerUp());
    	addSequential(new DealWithContainer());
    	
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
