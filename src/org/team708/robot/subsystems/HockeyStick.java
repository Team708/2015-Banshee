package org.team708.robot.subsystems;

import org.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This is the subsystem for the hockey stick.
 * It uses a double solenoid to extend and retract it.
 * @author Alex Tysak and Nam Tran
 *
 */
public class HockeyStick extends Subsystem {

	private final DoubleSolenoid hockeySolenoid;		//Hockey stick solenoid
	
	public final DoubleSolenoid.Value DEPLOYED = DoubleSolenoid.Value.kForward;		//Extended Solenoid
	public final DoubleSolenoid.Value RETRACTED = DoubleSolenoid.Value.kReverse;	//Retracted Solenoid
	
	private final Talon hockeyStickMotor;
	
	public HockeyStick() {
		//Create the solenoid for the hockey stick piston
		hockeySolenoid = new DoubleSolenoid(RobotMap.hockeySolenoidA, RobotMap.hockeySolenoidB);
		hockeyStickMotor = new Talon(RobotMap.hockeyStickMotor);
	}
	
	//extends the hockey solenoid
	public void deployHockey(){
		hockeySolenoid.set(DEPLOYED);
	}
	
	//retracts the hockey solenoid
	public void retractHockey(){
		hockeySolenoid.set(RETRACTED);
	}
	
	public DoubleSolenoid.Value getSolenoidValue() {
		return hockeySolenoid.get();
	}
	
	public void initDefaultCommand() {
		
	}
}
