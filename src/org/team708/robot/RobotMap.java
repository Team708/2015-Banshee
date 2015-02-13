package org.team708.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @author omn0mn0m
 */
public class RobotMap {
	
	// Gamepad USB ports
	public static final int driverGamepad = 1;
	public static final int operatorGamepad = 2;
	
	// PWM Ports
	public static final int hockeyStickMotor = 0;
	
	// Drivetrain CAN Device IDs
	public static final int drivetrainLeftMotorMaster = 11;
	public static final int drivetrainLeftMotorSlave = 12;
	public static final int drivetrainRightMotorMaster = 13;
	public static final int drivetrainRightMotorSlave = 14;
	
	//Intake CAN Device IDs
	public static final int intakeLeftMotor = 21;
	public static final int intakeRightMotor = 22;		
	
	// Claw CAN Talon Device ID
	public static final int clawElevatorMotor = 31;
	
	// Claw Elevator CAN Talon Device ID
	public static final int clawFingerMotorSpike = 2;
	
	// Indexer Spike Relay Port
	public static final int indexerMotorLeft = 21;
	public static final int indexerMotorRight = 22;
	
	// Digital IO
	public static final int drivetrainEncoderA = 0;
	public static final int drivetrainEncoderB = 1;
	public static final int indexerEncoderA = 2;
	public static final int indexerEncoderB = 3;
	public static final int clawElevatorLowerLimit = 4;
	public static final int clawElevatorSeries = 5;
	public static final int clawElevatorUpperLimit = 6;

	// Analog sensor IDs
	public static final int gyro = 0;
	public static final int drivetrainIRSensor = 1;
	
	// Claw PCM Ports
	public static final int clawDoubleSolenoidA = 0;
	public static final int clawDoubleSolenoidB = 1;
	public static final int clawWristDoubleSolenoidA = 2;
	public static final int clawWristDoubleSolenoidB = 3;
	
	//Hockey stick PCM Ports
	public static final int hockeySolenoidA = 4;
	public static final int hockeySolenoidB = 5;
}
