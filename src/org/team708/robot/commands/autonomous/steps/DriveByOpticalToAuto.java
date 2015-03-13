package org.team708.robot.commands.autonomous.steps;

import org.team708.robot.commands.drivetrain.DriveWithOpticalSensor;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drives the robot into the auto zone by moving over the scoring platform and using it to tell
 * where the robot is by optical sensor
 */
public class DriveByOpticalToAuto extends CommandGroup {
    
    public  DriveByOpticalToAuto() {
    	
    	// Drive forward until the scoring platform/white tape is reached
    	addSequential(new DriveWithOpticalSensor(true));
    	
    	// Drive forward until the robot leaves the scoring platform/white tape
    	addSequential(new DriveWithOpticalSensor(false));
    }
}
