package org.team708.robot.commands.claw;

import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleClawOpen extends Command {

    public ToggleClawOpen() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	// Makes the solenoid reverse if it is forward
    	if(Robot.claw.getClawOpen() == DoubleSolenoid.Value.kForward) {
    		Robot.claw.setClawOpen(DoubleSolenoid.Value.kReverse);
    	} 
    	// Makes the solenoid forward if it is reverse
    	else if(Robot.claw.getClawOpen() == DoubleSolenoid.Value.kReverse) {
    		Robot.claw.setClawOpen(DoubleSolenoid.Value.kForward);
    	} 
    	// Makes the solenoid OFF is it si off or if it is doing weird things
    	else {
    		Robot.claw.setClawOpen(DoubleSolenoid.Value.kOff);
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
