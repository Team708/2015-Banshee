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
	
	// Relays
	public static final int clawFingerMotorSpike = 2;
	
	// Drivetrain CAN Device IDs
	public static final int drivetrainLeftMotorMaster = 11;
	public static final int drivetrainLeftMotorSlave1 = 12;
	public static final int drivetrainLeftMotorSlave2 = 13;
	public static final int drivetrainRightMotorMaster = 14; 
	public static final int drivetrainRightMotorSlave1 = 15;
	public static final int drivetrainRightMotorSlave2 = 16;
	
	// Other CAN Talons
	public static final int clawElevatorMotor = 31;
	
	// Digital IO
	public static final int clawElevatorEncoderA = 4;
	public static final int clawElevatorEncoderB = 5;

	//Intake CAN Device IDs
	public static final int intakeLeftMotor = 21;
	public static final int intakeRightMotor = 22;		
	
	// Analog sensor IDs
	public static final int gyro = 0;
	
	//Hockey stick Solenoids
	public static final int hockeySolenoidA = 4;
	public static final int hockeySolenoidB = 5;
	
	// Digital I/O Ports
	public static final int toteSwitch = 6;

	// PCM Ports
	public static final int clawDoubleSolenoidA = 2;
	public static final int clawDoubleSolenoidB = 3;
	public static final int clawWristDoubleSolenoidA = 4;
	public static final int clawWristDoubleSolenoidB = 5;
	
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
