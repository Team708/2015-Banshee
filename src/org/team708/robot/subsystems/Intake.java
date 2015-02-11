package org.team708.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANTalon;

import org.team708.robot.RobotMap;
import org.team708.robot.commands.intake.IntepretIntake;

/**
 * This is the subsystem for the tote intake system.
 * It uses two motors powered by CAN Talons and has a switch for when it has one.
 * @author omn0mn0m
 * @author frakerman1
 * @author wutnut
 */
public class Intake extends Subsystem {

	private CANTalon leftMotor, rightMotor;		// Motors for spinning the intake
	
	private boolean on = false;
	private boolean in = true;
	
	public Intake() {
		leftMotor = new CANTalon(RobotMap.intakeLeftMotor);
		rightMotor = new CANTalon(RobotMap.intakeRightMotor);
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new IntepretIntake());
	}
	
	public void set(double input){
		leftMotor.set(input);
		rightMotor.set(-input);
	}
	
	public boolean isOn() {
		return on;
	}
	
	public boolean isIn() {
		return in;
	}
	
	public void setOn(boolean on) {
		this.on = on;
	}
	
	public void setIn(boolean in) {
		this.in = in;
	}
	
	public void sendToDashboard() {
		SmartDashboard.putBoolean("Intake Power", isOn());
		SmartDashboard.putBoolean("Intake In", isIn());
	}
}
