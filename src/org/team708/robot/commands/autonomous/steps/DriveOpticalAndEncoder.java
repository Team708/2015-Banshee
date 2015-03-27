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
	
	private boolean goForward;
	
	double moveSpeed;

	public DriveOpticalAndEncoder(double distance, boolean goForward) {
		// Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        
        if (goForward) {
        	this.distance = distance;
        	moveSpeed = AutoConstants.ENCODER_SPEED;
        } else {
        	this.distance = -distance;
        	moveSpeed = -AutoConstants.ENCODER_SPEED;
        }
        
		this.goForward = goForward;
	}
	
    public DriveOpticalAndEncoder(double distance) {
        this(distance, true);
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
    	
    	Robot.drivetrain.haloDrive(moveSpeed, 0.0, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (goForward) {
    		return (Robot.drivetrain.getEncoderDistance() >= distance) || (reachedPlatform && leftPlatform);
    	} else {
    		return (Robot.drivetrain.getEncoderDistance() <= distance) || (reachedPlatform && leftPlatform);
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	boolean needsEncoderMove;
    	
    	if (goForward) {
    		needsEncoderMove = (Robot.drivetrain.getEncoderDistance() < distance);
    	} else {
    		needsEncoderMove = (Robot.drivetrain.getEncoderDistance() > distance);
    	}
    	
    	if ((reachedPlatform && leftPlatform) && needsEncoderMove) {
    		if (goForward) {
	    		while(Robot.drivetrain.getEncoderDistance() < AutoConstants.CLAW_LENGTH) {
	    			if (Robot.drivetrain.getEncoderDistance() >= distance) {
	    				break;
	    			}
	    			Robot.drivetrain.haloDrive(moveSpeed, 0.0, false);
	    		}
    		} else {
    			while(Robot.drivetrain.getEncoderDistance() > -AutoConstants.ROBOT_LENGTH) {
	    			if (Robot.drivetrain.getEncoderDistance() >= distance) {
	    				break;
	    			}
	    			Robot.drivetrain.haloDrive(moveSpeed, 0.0, false);
	    		}
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
