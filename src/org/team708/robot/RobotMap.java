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
	public static final int driverGamepad   = 1;
	public static final int operatorGamepad = 2;
	
	// PWM Ports
	public static final int hockeyStickMotor = 0;
	
	// Drivetrain CAN Device IDs
	public static final int drivetrainLeftMotorMaster  = 11;
	public static final int drivetrainLeftMotorSlave   = 12;
	public static final int drivetrainRightMotorMaster = 13;
	public static final int drivetrainRightMotorSlave  = 14;
	
	// Indexer CAN Device IDs
	public static final int indexerMotor  = 21;
	
	// Claw System CAN Talon Device ID
	public static final int clawElevatorMotor = 31;
	
	//Gucci Grabber CAN Device IDs
	public static final int gucciGrabberMotor  = 41;
//	public static final int gucciGrabberRightMotor = 42;	
	
	// Digital IO
	public static final int drivetrainEncoderA		= 0;
	public static final int drivetrainEncoderB		= 1;
	public static final int indexerEncoderA			= 2;
	public static final int indexerEncoderB			= 3;
	public static final int clawElevatorEncoderA	= 4;
	public static final int clawElevatorEncoderB	= 5;
	public static final int clawElevatorUpperSwitch	= 6;
	public static final int clawElevatorLowerSwitch	= 7;
	public static final int drivetrainOpticalSensor	= 8;

	// Analog sensor IDs
	public static final int gyro				= 0;
	public static final int indexerIRSensor		= 1;
	public static final int drivetrainIRSensor	= 2;
	
	// Claw PCM Ports
	public static final int clawDoubleSolenoidA      = 0;
	public static final int clawDoubleSolenoidB      = 1;
	public static final int clawWristDoubleSolenoidA = 2;
	public static final int clawWristDoubleSolenoidB = 3;
	
	//Hockey stick PCM Ports
	public static final int gucciGrabberSolenoidA = 4;
	public static final int gucciGrabberSolenoidB = 5;
}
