
package org.team708.robot.subsystems;

import org.team708.robot.RobotMap;
import org.team708.robot.commands.JoystickDrive;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Nam Tran & Victor Lourng
 */
public class Drivetrain extends Subsystem {
    
	private CANTalon leftMaster, leftSlave, rightMaster, rightSlave;		// Motor Controllers
	private RobotDrive drivetrain;		// FRC provided drivetrain class
	
	private BuiltInAccelerometer accelerometer;
	
	private Gyro gyro;
	
	public Drivetrain() {
		// Initializes motor controllers with device IDs from RobotMap
		leftMaster = new CANTalon(RobotMap.drivetrainLeftMotorMaster);
		leftSlave = new CANTalon(RobotMap.drivetrainLeftMotorSlave);
		rightMaster = new CANTalon(RobotMap.drivetrainRightMotorMaster);
		rightSlave = new CANTalon(RobotMap.drivetrainRightMotorSlave);
		
		// Initializes drivetrain class
		drivetrain = new RobotDrive(leftMaster, rightMaster);
		
		// Sets up master and slave
		setupMasterSlave();
		
		accelerometer = new BuiltInAccelerometer();
		gyro = new Gyro(RobotMap.gyro);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new JoystickDrive());
    }
    
    public void haloDrive(double move, double rotate) {
    	drivetrain.arcadeDrive(move, rotate);
    }
    
    public void tankDrive(double left, double right) {
    	drivetrain.tankDrive(left, right);
    }
    
    public void setupMasterSlave() {
    	leftSlave.changeControlMode(CANTalon.ControlMode.Follower);
		rightSlave.changeControlMode(CANTalon.ControlMode.Follower);
		
		leftSlave.set(leftMaster.getDeviceID());
		rightSlave.set(rightMaster.getDeviceID());
    }
    
    public void sendToDashboard() {
    	SmartDashboard.putNumber("Accelerometer X", accelerometer.getX());
    	SmartDashboard.putNumber("Accelerometer Y", accelerometer.getY());
    	SmartDashboard.putNumber("Accelerometer Z", accelerometer.getZ());
    	SmartDashboard.putNumber("Gyro angle", gyro.getAngle());
    	SmartDashboard.putNumber("Gyro Rate", gyro.getRate());
    }
}

