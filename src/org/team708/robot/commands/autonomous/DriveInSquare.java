package org.team708.robot.commands.autonomous;

import org.team708.robot.commands.drivetrain.DriveStraightForTime;
import org.team708.robot.commands.drivetrain.TurnToDegrees;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveInSquare extends CommandGroup {
	
	private static final double driveStraightSpeed = 0.5;
	private static final double driveStraightTime = 2.0;
	
	private static final double turnSpeed = 0.5;
	private static final double turnDegrees = 90;
    
    public  DriveInSquare() {
    	addSequential(new DriveStraightForTime(driveStraightSpeed, driveStraightTime));
    	addSequential(new TurnToDegrees(turnSpeed, turnDegrees));
    	addSequential(new DriveStraightForTime(driveStraightSpeed, driveStraightTime));
    	addSequential(new TurnToDegrees(turnSpeed, turnDegrees));
    	addSequential(new DriveStraightForTime(driveStraightSpeed, driveStraightTime));
    	addSequential(new TurnToDegrees(turnSpeed, turnDegrees));
    	addSequential(new DriveStraightForTime(driveStraightSpeed, driveStraightTime));
    	addSequential(new TurnToDegrees(turnSpeed, turnDegrees));
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
