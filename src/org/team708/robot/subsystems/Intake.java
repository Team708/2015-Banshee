package org.team708.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

import org.team708.robot.RobotMap;
import org.team708.robot.commands.intake.IntepretIntake;

/**
 * This is the subsystem for the tote intake system.
 * It uses two motors powered by CAN Talons and has a switch for when it has one.
 * @author Nam Tran, Alex Tysak, Tommy Zhao
 *
 */
public class Intake extends Subsystem {

	private CANTalon leftMotor, rightMotor;		// Motors for spinning the intake
	
	private DigitalInput toteSwitch;			// Switch to detect when has switch
	
	private boolean on = false;
	private boolean in = true;
	
	public static final double IN_SPEED = 1.0;
	public static final double OUT_SPEED = -1.0;
	
	public Intake() {
		
		leftMotor = new CANTalon(RobotMap.intakeLeftMotor);
		rightMotor = new CANTalon(RobotMap.intakeRightMotor);
		
		toteSwitch = new DigitalInput(RobotMap.toteSwitch);
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
	
	public boolean isToteSwitchTriggered() {
		return !toteSwitch.get(); // Switched because the limit switch is wired to be true when not pressed and we want the opposite result
	}
	
	public void sendToDashboard() {
		SmartDashboard.putBoolean("Intake Power", on);
		SmartDashboard.putBoolean("Intake In", in);
	}
}
