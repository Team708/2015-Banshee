package org.team708.robot.commands.autonomous.optical;

import org.team708.robot.AutoConstants;
import org.team708.robot.commands.autonomous.steps.DriveOpticalAndEncoder;
import org.team708.robot.commands.claw.CloseClaw;
import org.team708.robot.commands.clawElevator.ClawUp;
import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.team708.robot.commands.drivetrain.TurnToDegrees;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ContainerToAutoZoneByOptical extends CommandGroup {
	
    public  ContainerToAutoZoneByOptical() {
    	addSequential(new CloseClaw());
    	addSequential(new ClawUp());
    	addSequential(new WaitCommand(.2));
    	addSequential(new DriveOpticalAndEncoder(AutoConstants.CONTAINER_TO_AUTOZONE_DISTANCE));  
    	
    	addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, -AutoConstants.NINETY_DEGREE_TURN));
    }
}
