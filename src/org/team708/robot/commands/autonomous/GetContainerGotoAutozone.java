package org.team708.robot.commands.autonomous;

import org.team708.robot.commands.claw.ToggleClawOpen;
import org.team708.robot.commands.clawElevator.IncrementClawOne;
import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GetContainerGotoAutozone extends CommandGroup {
    
	private final double DRIVE_DISTANCE = 70.0; //inches
	
    public  GetContainerGotoAutozone() {
    	addSequential(new IncrementClawOne());
    	addSequential(new WaitCommand(.2));
    	addSequential(new DriveStraightToEncoderDistance(DRIVE_DISTANCE));   
    }
}
