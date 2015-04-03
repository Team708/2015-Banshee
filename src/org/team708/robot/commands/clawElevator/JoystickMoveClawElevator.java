package org.team708.robot.commands.clawElevator;

import org.team708.robot.OI;
import org.team708.robot.Robot;
import org.team708.robot.util.Gamepad;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickMoveClawElevator extends Command {

    public JoystickMoveClawElevator() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.clawElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double moveSpeed = OI.operatorGamepad.getAxis(Gamepad.rightStick_Y);
    	
    	if (Robot.clawElevator.getUpperSwitch()) {
    		if (moveSpeed > 0.0) {
    			moveSpeed = 0.0;
    		}
    	} else if (Robot.clawElevator.getLowerSwitch()) {
    		if (moveSpeed < 0.0) {
    			moveSpeed = 0.0;
    		}
    	} else {}
    	
    	Robot.clawElevator.manualMove(moveSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
