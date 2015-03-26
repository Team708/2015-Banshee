package org.team708.robot.commands.drivetrain;

import org.team708.robot.Constants;
import org.team708.robot.Robot;
//import org.team708.robot.util.Math708;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightToEncoderDistance extends Command {

	private double targetDistance;
	private final double rotate = 0.0;
	private double speed;
	
	private boolean goForward;
	
    public DriveStraightToEncoderDistance(double distance) {
    	this(distance, Constants.DRIVE_MOTOR_MAX_SPEED);
    }
    
    public DriveStraightToEncoderDistance(double distance, double speed) {
    	this(distance, speed, true);
    }
    
    public DriveStraightToEncoderDistance(double distance, double speed, boolean goForward) {
    	// Use requires() here to declare subsystem dependencies
    	requires(Robot.drivetrain);
    	
    	if(goForward) {
    		targetDistance = distance;
    		this.speed = speed;
    	} else {
    		targetDistance = -distance;
    		this.speed = -speed;
    	}
    	
    	this.goForward = goForward;
    }

    // Called just before this Command runs the first time
    // Enables the PIDController (if it was not already) before attempting to drive straight
    protected void initialize() {
    	Robot.drivetrain.resetEncoder();
    	Robot.drivetrain.resetGyro();
//    	Robot.drivetrain.enable();
    	Robot.drivetrain.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.haloDrive(speed, rotate, false);
//    	Robot.drivetrain.haloDrive(Math708.getPercentError
//    			(Robot.drivetrain.getEncoderDistance(), targetDistance), rotate);    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(goForward) {
        	return (Robot.drivetrain.getEncoderDistance() >= targetDistance);
        } else {
        	return (Robot.drivetrain.getEncoderDistance() <= targetDistance);
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.disable();
    	Robot.drivetrain.stop();
    	Robot.drivetrain.resetEncoder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
