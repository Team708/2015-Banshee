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
    	Robot.clawElevator.manualMove(OI.operatorGamepad.getAxis(Gamepad.rightStick_Y));
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
