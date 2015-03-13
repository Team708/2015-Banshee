package org.team708.robot.commands.autonomous.steps;

import org.team708.robot.AutoConstants;
import org.team708.robot.Constants;
import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveOpticalAndEncoder extends Command {
	
	private boolean reachedPlatform = false;
	private boolean leftPlatform = false;
	
	private double distance;

    public DriveOpticalAndEncoder(double distance) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        
        this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!reachedPlatform) {
    		reachedPlatform = Robot.drivetrain.isOpticalSensorWhite();
    	} else {
    		if (!leftPlatform) {
    			leftPlatform = !Robot.drivetrain.isOpticalSensorWhite();
    		}
    	}
    	
    	Robot.drivetrain.haloDrive(AutoConstants.ENCODER_SPEED, 0.0, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.drivetrain.getEncoderDistance() >= distance) || (reachedPlatform && leftPlatform);
    }

    // Called once after isFinished returns true
    protected void end() {
    	if ((reachedPlatform && leftPlatform) && (Robot.drivetrain.getEncoderDistance() < distance)) {
    		while(Robot.drivetrain.getEncoderDistance() < AutoConstants.CLAW_LENGTH) {
    			if (Robot.drivetrain.getEncoderDistance() >= distance) {
    				break;
    			}
    			Robot.drivetrain.haloDrive(AutoConstants.ENCODER_SPEED, 0.0, false);
    		}
    		Robot.drivetrain.stop();
    	} else {
    		Robot.drivetrain.stop();
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
