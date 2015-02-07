package org.team708.robot.commands.autonomous;

import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *Check
 */
public class DealWithContainer extends Command {
	
	private final double SMACK_TURN_ANGLE = 90.0;
	private final double TURN_TOLERANCE = 5.0;

    public DealWithContainer() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.visionProcessor.processData();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.visionProcessor.isHasContainer()) {
    		Robot.hockeyStick.deployHockey();
    		Robot.drivetrain.resetGyro();
    		Robot.drivetrain.haloDrive(0.0, Robot.drivetrain.rotateByGyro(SMACK_TURN_ANGLE, TURN_TOLERANCE));
    		Robot.drivetrain.haloDrive(0.0, Robot.drivetrain.rotateByGyro(0.0, TURN_TOLERANCE));
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
