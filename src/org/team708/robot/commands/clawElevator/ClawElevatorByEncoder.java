package org.team708.robot.commands.clawElevator;

import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClawElevatorByEncoder extends Command {
	
	private double targetDistance;
	
	private boolean up;
	private boolean atLimit;

    public ClawElevatorByEncoder(double targetDistance, boolean up) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.clawElevator);
        
        this.targetDistance = targetDistance;
        this.up = up;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.clawElevator.resetEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (up) {
    		Robot.clawElevator.moveUp();
    		atLimit = Robot.clawElevator.getUpperSwitch();
    	} else {
    		Robot.clawElevator.moveDown();
    		atLimit = Robot.clawElevator.getLowerSwitch();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return atLimit || (Math.abs(Robot.clawElevator.getTravelDistance()) >= targetDistance);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.clawElevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
