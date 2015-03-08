package org.team708.robot.commands.drivetrain;

import org.team708.robot.Constants;
import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithOpticalSensor extends Command {
	
	private final double DRIVE_SPEED = 1.0;
	
	private boolean toWhite;

	/**
	 * Constructor
	 * @param toWhite
	 */
    public DriveWithOpticalSensor(boolean toWhite) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        
        this.toWhite = toWhite;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.haloDrive(DRIVE_SPEED * Constants.DRIVE_MOTOR_MAX_SPEED, 0.0, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return toWhite ? Robot.drivetrain.isOpticalSensorWhite() : !Robot.drivetrain.isOpticalSensorWhite();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
