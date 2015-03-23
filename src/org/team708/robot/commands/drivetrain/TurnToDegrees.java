package org.team708.robot.commands.drivetrain;

import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToDegrees extends Command {
	
	private double rotationSpeed;
	private double goalDegrees;

	/**
	 * Constructor
	 * @param rotationSpeed
	 * @param goalDegrees
	 */
    public TurnToDegrees(double rotationSpeed, double goalDegrees) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        
        this.rotationSpeed = rotationSpeed;
        this.goalDegrees = goalDegrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (goalDegrees >= 0) {
    		Robot.drivetrain.haloDrive(0.0, -rotationSpeed, true);
    	} else {
    		Robot.drivetrain.haloDrive(0.0, rotationSpeed, true);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (goalDegrees >= 0) {
    		return (Robot.drivetrain.getAngle() >= goalDegrees);
    	} else {
    		return (Robot.drivetrain.getAngle() <= goalDegrees);
    	}
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
