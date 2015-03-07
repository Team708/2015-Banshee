package org.team708.robot.commands.autonomous;

import org.team708.robot.commands.claw.ToggleClawOpen;
import org.team708.robot.commands.clawElevator.IncrementClawOne;
import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.team708.robot.commands.drivetrain.TurnToDegrees;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GetContainerGotoAutozone extends CommandGroup {
    
	private final double DRIVE_DISTANCE = 73.0; //inches
	private final double TURN_SPEED     = 0.6;
	private final double FINAL_TURN_ANGLE = -90.0; //degrees
	
    public  GetContainerGotoAutozone() {
    	addSequential(new IncrementClawOne());
    	addSequential(new WaitCommand(.2));
    	addSequential(new DriveStraightToEncoderDistance(DRIVE_DISTANCE));   
    	
    	addSequential(new TurnToDegrees(TURN_SPEED, FINAL_TURN_ANGLE));
    }
}
