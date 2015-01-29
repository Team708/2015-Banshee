package org.team708.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;

import org.team708.robot.RobotMap;
import org.team708.robot.commands.JoystickIntake;

public class Intake extends Subsystem {

	private CANTalon leftMotor, rightMotor;
	
	public Intake() {
		
		leftMotor = new CANTalon(RobotMap.intakeLeftMotor);
		rightMotor = new CANTalon(RobotMap.intakeRightMotor);
		
		
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoystickIntake());
	}
	
	public void intake(double input){
		leftMotor.set(input);
		rightMotor.set(-input);
	}

}
