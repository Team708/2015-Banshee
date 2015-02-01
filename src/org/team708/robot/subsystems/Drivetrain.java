package org.team708.robot.subsystems;

import org.team708.robot.RobotMap;
import org.team708.robot.commands.drivetrain.JoystickDrive;
import org.team708.robot.util.IRSensor;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is a drivetrain subsystem that uses PID to drive straight.
 * @author Nam Tran & Victor Lourng
 */
public class Drivetrain extends PIDSubsystem {
	
	// PID Tuning parameters
	private static double Kp = 0.20;		// Proportional gain		// Was 0.05 for 4in colsons
	private static double Ki = 0.0;		// Integral gain			// Was 0.01 for 4in colsons
	private static double Kd = 0.1;		// Derivative gain
	private static double tolerance = 5;
	
	// Variables specific for drivetrain PID loop
	private double moveSpeed = 0.0;
	private double pidOutput = 0.0;
	private static final double tankControlTolerance = .025;
	
	private CANTalon leftMaster, leftSlave1, leftSlave2, rightMaster, rightSlave1, rightSlave2;		// Motor Controllers
	private RobotDrive drivetrain;		// FRC provided drivetrain class
	
	private BuiltInAccelerometer accelerometer;		// Accelerometer that is built into the roboRIO
	private Gyro gyro;								// Gyro that is used for drift correction
	
	private IRSensor irSensor;						// IR Sensor that is used for short distancing		
	
	private boolean brake = true;		// Whether the talons should be in coast or brake mode (this could be important if a jerky robot causes things to topple
	
    /**
     * Constructor
     */
    public Drivetrain() {
    	// Passes variables from this class into the superclass constructor
    	super("Drivetrain", Kp, Ki, Kd);
    	
    	// Initializes motor controllers with device IDs from RobotMap
		leftMaster = new CANTalon(RobotMap.drivetrainLeftMotorMaster);
		leftSlave1 = new CANTalon(RobotMap.drivetrainLeftMotorSlave1);
		leftSlave2 = new CANTalon(RobotMap.drivetrainLeftMotorSlave2);
		rightMaster = new CANTalon(RobotMap.drivetrainRightMotorMaster);
		rightSlave1 = new CANTalon(RobotMap.drivetrainRightMotorSlave1);
		rightSlave2 = new CANTalon(RobotMap.drivetrainRightMotorSlave2);
		
		drivetrain = new RobotDrive(leftMaster, rightMaster);		// Initializes drivetrain class
		
		setupMasterSlave();			// Sets up master and slave
		
		accelerometer = new BuiltInAccelerometer();		// Initializes the accelerometer from the roboRIO
		gyro = new Gyro(RobotMap.gyro);					// Initializes the gyro
		gyro.reset();									// Resets the gyro so that it starts with a 0.0 value
		irSensor = new IRSensor(RobotMap.drivetrainIRSensor, IRSensor.GP2Y0A21YK0F);
    	
		setAbsoluteTolerance(tolerance);
		setInputRange(-360.0, 360);
        setSetpoint(0.0);
        disable();
    }
    
    /**
     * Initializes the default command for this subsystem
     */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new JoystickDrive());
    }
    
    /**
     * Drives the drivetrain using a forward-backward value and a rotation value
     * @param move
     * @param rotate
     */
    public void haloDrive(double move, double rotate) {
//    	// Checks whether drift correction is needed
//    	if (rotate == 0.0 && move != 0.0) {
//    		// Enables the PID controller if it is not already
//    		if (!getPIDController().isEnable()) {
//    			gyro.reset();
//    			enable();
//    		}
//    		// Sets the forward move speed to the move parameter
//    		moveSpeed = move;
//    	} else {
//    		// Disables the PID controller if it enabled so the drivetrain can move freely
//    		if (getPIDController().isEnable()) {
//    			disable();
//    		}
    		drivetrain.arcadeDrive(move, rotate);
//    	}
    }
    
    /**
     * Drives the drivetrain using a left motor(s) value and a right motor(s) value
     * @param left
     * @param right
     */
    public void tankDrive(double left, double right) {
    	// Checks whether drift correction is needed
    	if (Math.abs(left - right) < tankControlTolerance && left != 0.0 && right != 0.0) {
    		// Enables the PID controller if it is not already
    		if (!getPIDController().isEnable()) {
    			enable();
    		}
    		// Sets the forward move speed to the average of the two sticks
    		moveSpeed = ((left + right) / 2);
    	} else {
    		// Disables the PID controller if it enabled so the drivetrain can move freely
    		if (getPIDController().isEnable()) {
    			disable();
    		}
    		drivetrain.tankDrive(left, right);
    	}
    }
    
    public void stop() {
    	leftMaster.set(0.0);
    	rightMaster.set(0.0);
    }
    
    /**
     * Gets the degrees that the gyro is reading
     * @return
     */
    public double getAngle() {
    	return gyro.getAngle();
    }
    
    /**
     * Resets the gyro reading
     */
    public void resetGyro() {
    	gyro.reset();
    }
    
    public double getIRDistance() {
    	return irSensor.getDistance();
    }
    
    /**
     * Returns the move speed of the robot needed to get to a certain IR distance reading.
     * This assumes that the IR sensor is in the front of the robot.
     * @param targetDistance
     * @return
     */
    public double moveByIR(double targetDistance, double tolerance) {
    	double difference = getIRDistance() - targetDistance;
    	
    	if (Math.abs(difference) <= tolerance) {
    		difference = 0.0;
    	}
    	
    	return difference / targetDistance;
    }
    
    /**
     * Sets up the drivetrain motors to have a master that is controlled by the 
     * default FRC RobotDrive class and slaves that do whatever the master
     * talon is doing
     */
    public void setupMasterSlave() {
    	leftSlave1.changeControlMode(CANTalon.ControlMode.Follower);
		leftSlave2.changeControlMode(CANTalon.ControlMode.Follower);
		rightSlave1.changeControlMode(CANTalon.ControlMode.Follower);
		rightSlave2.changeControlMode(CANTalon.ControlMode.Follower);
		
		leftSlave1.set(leftMaster.getDeviceID());
		leftSlave2.set(leftMaster.getDeviceID());
		rightSlave1.set(rightMaster.getDeviceID());
		rightSlave2.set(rightMaster.getDeviceID());
    }
    
    /**
     * Toggles between brake and coast mode for the talons
     */
    public void toggleBrakeMode() {
    	brake = !brake;
    	leftMaster.enableBrakeMode(brake);
    	leftSlave1.enableBrakeMode(brake);
    	leftSlave2.enableBrakeMode(brake);
    	rightMaster.enableBrakeMode(brake);
    	rightSlave1.enableBrakeMode(brake);
    	rightSlave2.enableBrakeMode(brake);
    }
    
    /**
     * Returns a process variable to the PIDSubsystem for correction
     */
    protected double returnPIDInput() {
    	return gyro.getAngle();
    }
    
    /**
     * Performs actions using the robot to correct for any error using the outputed value
     */
    protected void usePIDOutput(double output) {
        pidOutput = output;
        drivetrain.arcadeDrive(moveSpeed, -output);
    }
    
    /**
     * Returns the PID gain constant
     * @param gain
     * @return
     */
    public double getPIDGain(char gain) {
    	switch(gain) {
    		case 'p':
    			return Kp;
    		case 'i':
    			return Ki;
    		case 'd':
    			return Kd;
    		default:
    			return 0.0;
    	}
    }
    
    /**
     * Sets the PID gain constant to the desired value
     * @param gain
     * @param value
     */
    public void setPIDGain(char gain, double value) {
    	switch(gain) {
		case 'p':
			Kp = value;
			break;
		case 'i':
			Ki = value;
			break;
		case 'd':
			Kd = value;
			break;
		default:
			break;
	}
    }
    
    /**
     * Sends data for this subsystem to the dashboard
     */
    public void sendToDashboard() {
    	SmartDashboard.putNumber("Accelerometer X", accelerometer.getX());
    	SmartDashboard.putNumber("Accelerometer Y", accelerometer.getY());
    	SmartDashboard.putNumber("Accelerometer Z", accelerometer.getZ());
    	SmartDashboard.putNumber("Gyro angle", gyro.getAngle());
    	SmartDashboard.putNumber("Gyro Rate", gyro.getRate());
    	SmartDashboard.putBoolean("Brake", brake);
    	SmartDashboard.putNumber("PID Output", pidOutput);
    	SmartDashboard.putNumber("IR Reading", getIRDistance());
    }
}
