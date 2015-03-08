package org.team708.robot.commands.intake;

import org.team708.robot.Constants;
import org.team708.robot.OI;
import org.team708.robot.Robot;
import org.team708.robot.util.Gamepad;

import edu.wpi.first.wpilibj.command.Command;

public class JoystickIntake extends Command {
	
	private double offZone = 0.5;

	public JoystickIntake() {
		requires(Robot.intake);
	}

	protected void initialize() {
	}

	protected void execute() {
		double joystickInput = OI.operatorGamepad.getAxis(Gamepad.rightStick_Y);
		
		if (joystickInput > 0.0) {
			Robot.intake.set(Constants.MOTOR_REVERSE);
		} else if (joystickInput < 0.0) {
			Robot.intake.set(Constants.MOTOR_FORWARD);
		} else {
			Robot.intake.set(Constants.MOTOR_OFF);
		}
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	protected void end() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
