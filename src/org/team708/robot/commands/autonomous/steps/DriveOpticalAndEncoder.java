package org.team708.robot.commands.autonomous.steps;

import org.team708.robot.AutoConstants;
import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
        
        this.distance = distance;
        
        if (goForward) {
        	moveSpeed = AutoConstants.ENCODER_SPEED;
        } else {
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
    	Robot.drivetrain.haloDrive(moveSpeed, 0.0, false);
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
    	return (Math.abs(Robot.drivetrain.getEncoderDistance()) >= distance) || (reachedPlatform && leftPlatform);
    }

    // Called once after isFinished returns true
    protected void end() {
    	if ((reachedPlatform && leftPlatform) && Math.abs(Robot.drivetrain.getEncoderDistance()) < distance) {
    		double initialDistance = Math.abs(Robot.drivetrain.getEncoderDistance());
    		
    		Robot.drivetrain.resetEncoder();
    		
    		boolean continueWhileLoop = true;
    		
    		while (continueWhileLoop) {
    			if ((initialDistance + Math.abs(Robot.drivetrain.getEncoderDistance())) >= distance) {
    				break;
    			}
    			
    			Robot.drivetrain.haloDrive(moveSpeed, 0.0, false);
    			
    			if (goForward) {
    				continueWhileLoop = Robot.drivetrain.getEncoderDistance() < AutoConstants.CLAW_LENGTH;
    			} else {
    				continueWhileLoop = Math.abs(Robot.drivetrain.getEncoderDistance()) < AutoConstants.ROBOT_LENGTH;
    			}
    		}
    	}
    	
    	Robot.drivetrain.stop();
    	Robot.drivetrain.resetEncoder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
