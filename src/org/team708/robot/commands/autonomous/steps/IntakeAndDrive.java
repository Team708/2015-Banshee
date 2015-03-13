package org.team708.robot.commands.autonomous.steps;

import org.team708.robot.AutoConstants;
import org.team708.robot.Constants;
import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeAndDrive extends Command {

	private double distance;
	
    public IntakeAndDrive(double distance) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intake.set(Constants.MOTOR_FORWARD);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.haloDrive(AutoConstants.ENCODER_SPEED, 0.0, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.getEncoderDistance() >= distance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.set(Constants.MOTOR_OFF);
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
