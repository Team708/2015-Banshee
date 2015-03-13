package org.team708.robot.commands.autonomous.steps;

import org.team708.robot.AutoConstants;
import org.team708.robot.Constants;
import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves containers out of the way
 */
public class DealWithContainer extends Command {
	
	private double distance;

    public DealWithContainer(double distance) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        
        this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intake.set(Constants.MOTOR_REVERSE);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.haloDrive(AutoConstants.ENCODER_SPEED, 0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.getEncoderDistance() >= distance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    	Robot.intake.set(Constants.MOTOR_OFF);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
