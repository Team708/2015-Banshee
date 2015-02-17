package org.team708.robot.util;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

public class HatterDrive extends RobotDrive {

	private double turnSensitivity = 1.5; // How sensitive turning is for the
											// drivetrain
	private double driveStall = 0.1; // What percent power before the drivetrain
										// can move
	private boolean squaredInputs;
	
	private final boolean USE_SAFETY = false;
	
	public HatterDrive(int leftMotorChannel, int rightMotorChannel, boolean squaredInputs) {
		super(leftMotorChannel, rightMotorChannel);
		this.setSafetyEnabled(USE_SAFETY);
		this.squaredInputs = squaredInputs;
	}

	public HatterDrive(SpeedController leftMotor, SpeedController rightMotor, boolean squaredInputs) {
		super(leftMotor, rightMotor);
		this.setSafetyEnabled(USE_SAFETY);
		this.squaredInputs = squaredInputs;
	}

	/**
	 * Borrowed from Team 254, this allows for easily controlling a fast drivetrain
	 * 
	 * @param move
	 * @param rotate
	 * @param quickTurn
	 */
	public void cheesyDrive(double move, double rotate, boolean quickTurn) {
		double angular_power = 0.0;
		double overPower = 0.0;
		double sensitivity = turnSensitivity;
		double rPower = 0.0;
		double lPower = 0.0;

		if (quickTurn) {
			overPower = 1.0;
			sensitivity = 1.0;
			angular_power = rotate;
		} else {
			overPower = 0.0;
			angular_power = Math.abs(move) * rotate * sensitivity;
		}
		rPower = lPower = move;
		lPower += angular_power;
		rPower -= angular_power;
		if (lPower > 1.0) {
			rPower -= overPower * (lPower - 1.0);
			lPower = 1.0;
		} else if (rPower > 1.0) {
			lPower -= overPower * (rPower - 1.0);
			rPower = 1.0;
		} else if (lPower < -1.0) {
			rPower += overPower * (-1.0 - lPower);
			lPower = -1.0;
		} else if (rPower < -1.0) {
			lPower += overPower * (-1.0 - rPower);
			rPower = -1.0;
		}
		
		lPower = correctDriveStall(lPower);
		rPower = correctDriveStall(rPower);
		setLeftRightMotorOutputs(lPower, rPower);
	}
	
	/**
	 * Drives the robot using a move value and a rotation value
	 */
	public void arcadeDrive(double move, double rotate) {
		super.arcadeDrive(move, rotate, squaredInputs);
	}
	
	/**
	 * Drives the robot using a left side value and a right side value
	 */
	public void tankDrive(double left, double right) {
		super.tankDrive(left, right, squaredInputs);
	}
	
	/**
	 * Returns if the inputs for the drivetrain is squared
	 * @return
	 */
	public boolean isSquaredInputs() {
		return squaredInputs;
	}
	
	/**
	 * Sets whether the robot should be squaring its drivetrain inputs
	 * @param squaredInputs
	 */
	public void setSquaredInputs(boolean squaredInputs) {
		this.squaredInputs = squaredInputs;
	}
	
	/**
	 * Similar to the deadband method in the Gamepad class, this corrects if the input is where nothing
	 * should happen (0.0)
	 * 
	 * @param input
	 * @param deadband
	 * @return
	 */
	public double correctDriveStall(double input) {
		double output = 0.0;
		
		if (Math.abs(input) > Math.abs(driveStall)) {
			output = input;
		}
		
		return output;
	}
}
