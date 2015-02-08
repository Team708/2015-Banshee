package org.team708.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// Gamepad USB ports
	public static final int driverGamepad = 1;
	public static final int operatorGamepad = 2;
	
	// Drivetrain CAN Device IDs
	public static final int drivetrainLeftMotorMaster = 11;
	public static final int drivetrainLeftMotorSlave = 12;
	public static final int drivetrainRightMotorMaster = 13;
	public static final int drivetrainRightMotorSlave = 14;
	
	// Tote Elevator CAN Device IDs
	public static final int toteElevatorMotor1 = 21;
	public static final int toteElevatorMotor2 = 22;
	
	//Intake CAN Device IDs
	public static final int intakeLeftMotor = 21;
	public static final int intakeRightMotor = 22;		
	
	// Other CAN Talons
	public static final int clawElevatorMotor = 31;
	
	// Relays
	public static final int clawFingerMotorSpike = 2;
	
	// Digital IO
	public static final int drivetrainEncoderA = 0;
	public static final int drivetrainEncoderB = 1;
	public static final int clawElevatorEncoderA = 4;
	public static final int clawElevatorEncoderB = 5;

	// Analog sensor IDs
	public static final int gyro = 0;
	public static final int drivetrainIRSensor = 1;
	
	// Digital I/O Ports
	public static final int toteSwitch = 6;

	// Digital Input Output
	public static final int toteElevatorEncoderA = 2;
	public static final int toteElevatorEncoderB = 3;
	
	//Hockey stick PCM Ports
	public static final int hockeySolenoidA = 4;
	public static final int hockeySolenoidB = 5;
	
	// Claw PCM Ports
	public static final int clawDoubleSolenoidA = 2;
	public static final int clawDoubleSolenoidB = 3;
	public static final int clawWristDoubleSolenoidA = 6;
	public static final int clawWristDoubleSolenoidB = 7;
	
	// PWM Ports
	public static final int hockeyStickMotor = 3;
	
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
