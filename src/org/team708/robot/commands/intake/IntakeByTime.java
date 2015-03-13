package org.team708.robot.commands.intake;

import org.team708.robot.Constants;
import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeByTime extends Command {
	
	private double runTime;

    public IntakeByTime(double runTime) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
        
        this.runTime = runTime;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intake.set(Constants.MOTOR_FORWARD);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (timeSinceInitialized() >= runTime);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.set(Constants.MOTOR_OFF);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
