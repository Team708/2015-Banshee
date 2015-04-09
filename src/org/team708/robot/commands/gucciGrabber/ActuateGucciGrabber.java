package org.team708.robot.commands.gucciGrabber;

import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ActuateGucciGrabber extends Command {
	
	private boolean deploy;

    public ActuateGucciGrabber(boolean deploy) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	this.deploy = deploy;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(deploy) {
    		Robot.gucciGrabber.deploy();
    	} else {
    		Robot.gucciGrabber.retract();
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
    }
}
