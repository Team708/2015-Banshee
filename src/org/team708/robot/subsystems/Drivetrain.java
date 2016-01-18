package org.team708.robot.subsystems;

import org.team708.robot.Constants;
import org.team708.robot.RobotMap;
import org.team708.robot.commands.drivetrain.JoystickDrive;
import org.team708.robot.util.HatterDrive;
import org.team708.robot.util.IRSensor;
import org.team708.robot.util.Math708;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is a drivetrain subsystem that uses PID to drive straight.
 * @author Nam Tran & Victor Lourng
 */

public class Drivetrain extends PIDSubsystem {
	
	private boolean usePID = true;
	
	// Variables specific for drivetrain PID loop
	private double moveSpeed = 0.0;
	private double pidOutput = 0.0;
	
	private CANTalon leftMaster, leftSlave, rightMaster, rightSlave;		// Motor Controllers
	
	private HatterDrive drivetrain;											// FRC provided drivetrain class
	
	private Encoder encoder;												// Encoder for the drivetrain
	private double distancePerPulse;
	private BuiltInAccelerometer accelerometer;								// Accelerometer that is built into the roboRIO
	private Gyro gyro;														// Gyro that is used for drift correction
	
//	private IRSensor drivetrainIRSensor;									// IR Sensor that is used for short distancing
	private DigitalInput opticalSensor;
	
	private boolean brake = true;		// Whether the talons should be in coast or brake mode (this could be important if a jerky robot causes things to topple
	
    /**
     * Constructor
     */
    public Drivetrain() {
    	// Passes variables from this class into the superclass constructor
    	super("Drivetrain", Constants.Kp, Constants.Ki, Constants.Kd);
    	
    	// Initializes motor controllers with device IDs from RobotMap
		leftMaster  = new CANTalon(RobotMap.drivetrainLeftMotorMaster);
		leftSlave   = new CANTalon(RobotMap.drivetrainLeftMotorSlave);
		rightMaster = new CANTalon(RobotMap.drivetrainRightMotorMaster);
		rightSlave  = new CANTalon(RobotMap.drivetrainRightMotorSlave);
		
		drivetrain = new HatterDrive(leftMaster, rightMaster, Constants.DRIVE_USE_SQUARED_INPUT);		// Initializes drivetrain class
		
		setupMasterSlave();								// Sets up master and slave
		
		accelerometer = new BuiltInAccelerometer();		// Initializes the accelerometer from the roboRIO
		gyro = new AnalogGyro(RobotMap.gyro);			// Initializes the gyro
		gyro.reset();									// Resets the gyro so that it starts with a 0.0 value
		encoder = new Encoder(RobotMap.drivetrainEncoderA, RobotMap.drivetrainEncoderB, Constants.DRIVETRAIN_USE_LEFT_ENCODER);
														// Initializes the encoder
		distancePerPulse = (Constants.DRIVETRAIN_WHEEL_DIAMETER * Math.PI) /
			(Constants.DRIVETRAIN_ENCODER_PULSES_PER_REV);
														// Sets the distance per pulse of the encoder to read distance properly
		encoder.setDistancePerPulse(distancePerPulse);
		encoder.reset();								// Resets the encoder so that it starts with a 0.0 value
		
//		drivetrainIRSensor = new IRSensor(RobotMap.drivetrainIRSensor, IRSensor.GP2Y0A21YK0F);
		opticalSensor = new DigitalInput(RobotMap.drivetrainOpticalSensor);
		
		setInputRange(-25.0, 25.0);
		setAbsoluteTolerance(Constants.pid_tolerance);
        setSetpoint(0.0);
//		enable();
        disable();
    }
    
    /**
     * Initializes the default command for this subsystem
     */
    @Override
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new JoystickDrive());
    }
    
    /**
     * Drives the drivetrain using a forward-backward value and a rotation value
     * @param move
     * @param rotate
     */
    public void haloDrive(double move, double rotate, boolean usePID) {
    	// Checks whether drift correction is needed
    	
    	// Sets multiplier for max drive speed
    	move = move * Constants.DRIVE_MOTOR_MAX_SPEED;
    	rotate = rotate * Constants.ROTATE_MOTOR_MAX_SPEED;
    	
    	if (usePID) {
	    	if (rotate == 0.0 && move > 0.0) {
	    		// Enables the PID controller if it is not already
	    		if (!getPIDController().isEnable()) {
	    			getPIDController().setPID(Constants.KpForward, Constants.KiForward, Constants.KdForward);
	    			getPIDController().reset();
	    			gyro.reset();
	    			enable();
	    			gyro.reset();
	    		}
	    		// Sets the forward move speed to the move parameter
	    		moveSpeed = move;
	    	} else if (rotate == 0.0 && move < 0.0){
	    		// Enables the PID controller if it is not already
	    		if (!getPIDController().isEnable()) {
	    			getPIDController().setPID(Constants.KpBackward, Constants.KiBackward, Constants.KdBackward);
	    			getPIDController().reset();
	    			gyro.reset();
	    			enable();
	    			gyro.reset();
	    		}
	    		// Sets the forward move speed to the move parameter
	    		moveSpeed = move;
	    	} else {
	    		// Disables the PID controller if it enabled so the drivetrain can move freely
	    		if (getPIDController().isEnable()) {
	    			getPIDController().reset();
	    		}
	    		drivetrain.arcadeDrive(move, rotate);
	    	}
    	} else {
    		// Disables the PID controller if it enabled so the drivetrain can move freely
    		if (getPIDController().isEnable()) {
    			getPIDController().reset();
    		}
    		drivetrain.arcadeDrive(move, rotate);
    	}
    }
	
	public void haloDrive(double move, double rotate) {
		haloDrive(move, rotate, this.usePID);
	}
    
    /**
     * Drives the drivetrain using a left motor(s) value and a right motor(s) value
     * @param left
     * @param right
     */
    public void tankDrive(double left, double right) {
    	// Checks whether drift correction is needed
    	if (Math.abs(left - right) < Constants.TANK_STICK_TOLERANCE && left != 0.0 && right != 0.0) {
    		// Enables the PID controller if it is not already
    		if (!getPIDController().isEnable()) {
    			gyro.reset();
    			getPIDController().reset();
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
	
	public boolean getUsePID() {
		return usePID;
	}
	
	public void setUsePID(boolean usePID) {
		this.usePID = usePID;
	}
    
    public void stop() {
    	leftMaster.set(Constants.MOTOR_OFF);
    	rightMaster.set(Constants.MOTOR_OFF);
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
    
//    public double rotateByGyro(double targetAngle, double tolerance) {
//    	double difference = getAngle() - targetAngle;
//    	
//    	if (Math708.isWithinThreshold(getIRDistance(), targetAngle, tolerance)) {
//    		difference = 0.0;
//    	}
//    	
//    	return difference / targetAngle;
//    }
    
//    public double getIRDistance() {
//    	return drivetrainIRSensor.getClippedAverageDistance();
//    }
    
//    /**
//     * Returns the move speed of the robot needed to get to a certain IR distance reading.
//     * This assumes that the IR sensor is in the front of the robot.
//     * @param targetDistance
//     * @return
//     */
//    public double moveByIR(double targetDistance, double minSpeed, double maxSpeed, double tolerance) {
//    	double value = Math708.getClippedPercentError(getIRDistance(), targetDistance, minSpeed, maxSpeed);
//    	
//    	if (value <= 0.0) {
//    		return 0.0;
//    	}
//    	return value;
//    }
    
    /**
     * Sets up the drivetrain motors to have a master that is controlled by the 
     * default FRC RobotDrive class and slaves that do whatever the master
     * talon is doing
     */
    public void setupMasterSlave() {
    	leftSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		
		leftSlave.set(leftMaster.getDeviceID());
		rightSlave.set(rightMaster.getDeviceID());
    }
    
    /**
     * Toggles between brake and coast mode for the talons
     */
    public void toggleBrakeMode() {
    	brake = !brake;
    	leftMaster.enableBrakeMode(brake);
    	leftSlave.enableBrakeMode(brake);
    	rightMaster.enableBrakeMode(brake);
    	rightSlave.enableBrakeMode(brake);
    }
    
    /**
     * Sets encoder direction depending on which side of the drivetrain it is on
     */
    public void setEncoderReading() {
    	encoder.setReverseDirection(Constants.DRIVETRAIN_USE_LEFT_ENCODER);
    }
    
    /**
     * 
     * @return Distance traveled since last encoder reset
     */
    public double getEncoderDistance() {
    	return encoder.getDistance();
    }
    
    /**
     * Resets the encoder to 0.0
     */
    public void resetEncoder() {
    	encoder.reset();
    }
    
    /**
     * Returns if the optical sensor detects the colour white
     * @return
     */
    public boolean isOpticalSensorWhite() {
    	return opticalSensor.get();
    }
    
    /**
     * Returns a process variable to the PIDSubsystem for correction
     */
    @Override
	protected double returnPIDInput() {
    	return gyro.getAngle();
    }
    
    /**
     * Performs actions using the robot to correct for any error using the outputed value
     */
    @Override
	protected void usePIDOutput(double output) {
        pidOutput = output;
        drivetrain.arcadeDrive(moveSpeed, -output);
    }
    
    /**
     * Sends data for this subsystem to the dashboard
     */
    public void sendToDashboard() {
    	if (Constants.DEBUG) {
	    	// Accelerometer Info
	    	SmartDashboard.putNumber("Accelerometer X", accelerometer.getX());
	    	SmartDashboard.putNumber("Accelerometer Y", accelerometer.getY());
	    	SmartDashboard.putNumber("Accelerometer Z", accelerometer.getZ());
	    	
	    	SmartDashboard.putNumber("Gyro Rate", gyro.getRate());			// Gyro rate
	    	SmartDashboard.putNumber("PID Output", pidOutput);				// PID Info
	    	SmartDashboard.putNumber("DT Encoder Raw", encoder.get());		// Encoder raw count
    	}
    	
    	SmartDashboard.putNumber("Gyro angle", gyro.getAngle());				// Gyro angle
    	SmartDashboard.putBoolean("Brake", brake);								// Brake or Coast
//    	SmartDashboard.putNumber("DT IR Distance", getIRDistance());			// IR distance reading
    	SmartDashboard.putNumber("DT Encoder Distance", encoder.getDistance());	// Encoder reading
//    	SmartDashboard.putBoolean("Over Scoring Platform", isOpticalSensorWhite());
    	
//    	SmartDashboard.putNumber("Move By IR Value", moveByIR(6.0,
//            		0.0, 0.9, 0.1));
    }
}
