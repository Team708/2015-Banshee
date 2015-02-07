package org.team708.robot.commands.autonomous;

import org.team708.robot.Robot;
import org.team708.robot.commands.claw.ClawHeightIncrement;
import org.team708.robot.commands.claw.ToggleClawOpen;
import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.team708.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GetContainerGotoAutozone extends CommandGroup {
    
	private final int driveDist = 17;
	
    public  GetContainerGotoAutozone() {
        // Add Commands here:
    	
    	
    	addSequential(new ToggleClawOpen());
    	addSequential(new DriveStraightToEncoderDistance(driveDist));
    	addSequential(new ClawHeightIncrement());
    	addSequential(new ToggleClawOpen());
    	
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
