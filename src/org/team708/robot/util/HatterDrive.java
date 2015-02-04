package org.team708.robot.util;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

public class HatterDrive extends RobotDrive {

	private double turnSensitivity = 1.5; // How sensitive turning is for the
											// drivetrain
	private double driveStall = 0.1; // What percent power before the drivetrain
										// can move
	
	public HatterDrive(int leftMotorChannel, int rightMotorChannel) {
		super(leftMotorChannel, rightMotorChannel);
		// TODO Auto-generated constructor stub
	}

	public HatterDrive(SpeedController leftMotor, SpeedController rightMotor) {
		super(leftMotor, rightMotor);
		// TODO Auto-generated constructor stub
	}

	public HatterDrive(int frontLeftMotor, int rearLeftMotor,
			int frontRightMotor, int rearRightMotor) {
		super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		// TODO Auto-generated constructor stub
	}

	public HatterDrive(SpeedController frontLeftMotor,
			SpeedController rearLeftMotor, SpeedController frontRightMotor,
			SpeedController rearRightMotor) {
		super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Taken from Team 254, this allows for easily controlling a fast drivetrain
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
