package org.team708.robot.commands.autonomous.encoder;

import org.team708.robot.AutoConstants;
import org.team708.robot.commands.claw.CloseClaw;
import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.team708.robot.commands.gucciGrabber.ActuateGucciGrabber;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CanGrabMoveBack extends CommandGroup {
    
    public  CanGrabMoveBack() {
    	addSequential(new CloseClaw());
    	
    	addParallel(new ActuateGucciGrabber(true));
        addSequential(new DriveStraightToEncoderDistance(AutoConstants.TO_CAN_DISTANCE, AutoConstants.ENCODER_SPEED, false));
        
        addSequential(new DriveStraightToEncoderDistance(AutoConstants.TO_CAN_DISTANCE, AutoConstants.ENCODER_SPEED));
    }
}
