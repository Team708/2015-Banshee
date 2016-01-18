package org.team708.robot.commands.autonomous.encoder;

import org.team708.robot.AutoConstants;
import org.team708.robot.commands.claw.CloseClaw;
import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.team708.robot.commands.drivetrain.TurnToDegrees;
import org.team708.robot.commands.gucciGrabber.ActuateGucciGrabber;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CanGrabMoveToNext extends CommandGroup {
    
    public  CanGrabMoveToNext() {
    	addParallel(new CloseClaw());
    	
        addSequential(new DriveStraightToEncoderDistanceOrTime(AutoConstants.TO_CAN_DISTANCE, AutoConstants.ENCODER_SPEED, false, AutoConstants.CAN_GRAB_TIMEOUT));
        addSequential(new ActuateGucciGrabber(true));
        addSequential(new WaitCommand(0.1));
        
        addSequential(new DriveStraightToEncoderDistance(AutoConstants.TO_CAN_DISTANCE, AutoConstants.CAN_GRAB_FORWARD_SPEED));
        addSequential(new ActuateGucciGrabber(false));
        addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, AutoConstants.NINETY_DEGREE_TURN));
        addSequential(new DriveStraightToEncoderDistance(AutoConstants.TO_SECOND_CAN_DISTANCE, AutoConstants.ENCODER_SPEED));
        
        addSequential(new TurnToDegrees(AutoConstants.TURN_SPEED, -AutoConstants.NINETY_DEGREE_TURN));
        addSequential(new DriveStraightToEncoderDistanceOrTime(AutoConstants.TO_CAN_DISTANCE, AutoConstants.ENCODER_SPEED, false, AutoConstants.CAN_GRAB_TIMEOUT));
        addParallel(new ActuateGucciGrabber(true));
        addSequential(new WaitCommand(0.1));
        addSequential(new DriveStraightToEncoderDistance(AutoConstants.TO_CAN_DISTANCE, AutoConstants.ENCODER_SPEED, false));
    }
}
