package org.team708.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;

/**
 * Class containing all the code-related constants, so wiring and
 * gamepad controls are not included
 * @author omn0mn0m
 */
public final class Constants {

	/*
	 * Motor Controllers
	 */
	public static final Relay.Value SPIKE_FORWARD = Relay.Value.kForward;
	public static final Relay.Value SPIKE_REVERSE = Relay.Value.kReverse;
	public static final Relay.Value SPIKE_OFF = Relay.Value.kOff;
	public static final double MOTOR_FORWARD = 1.0;
	public static final double MOTOR_REVERSE = -1.0;
	public static final double MOTOR_OFF = 0.0;
	public static final double DRIVE_MOTOR_MAX_SPEED = 0.7;
	public static final double ROTATE_MOTOR_MAX_SPEED = 0.7;
	
	/*
	 * Double Solenoids
	 */
	public static final DoubleSolenoid.Value OPEN = DoubleSolenoid.Value.kReverse;
	public static final DoubleSolenoid.Value CLOSED = DoubleSolenoid.Value.kForward;
	public static final DoubleSolenoid.Value VERTICAL = DoubleSolenoid.Value.kReverse;
	public static final DoubleSolenoid.Value HORIZONTAL = DoubleSolenoid.Value.kForward;
	public static final DoubleSolenoid.Value RETRACTED = DoubleSolenoid.Value.kReverse;
	public static final DoubleSolenoid.Value DEPLOYED = DoubleSolenoid.Value.kForward;
	
	/*
	 * Smart Dashboard
	 */
	public static final double SEND_STATS_INTERVAL = .25;		// Interval between statistic reporting in seconds
	public static final boolean DEBUG = false;
	
	/*
	 * Sensors
	 */
	public static final double IR_HAS_TOTE_DISTANCE = 6.0;
	public static final double ENCODER_BOTTOM_POSITION = 0.0;
	public static final double GRAYHILL_ENCODER_PULSES_PER_REVOLUTION = 256.0;
	
	/*
	 * Game Elements
	 */
	public static final double TOTE_HEIGHT = 13.5;
	
	/*
	 * Indexer
	 */
	public static final double INDEXER_SPROCKET_DIAMETER = 1.8;
	public static final int TOTE_LIMIT = 4;
	public static final double ADJUST_UP = 1.0;
	public static final double ADJUST_DOWN = 0.5;
	
	/*
	 * Claw Elevator
	 */
	public static final double CLAW_ELEVATOR_MOTOR_MINIMUM = 0.3;
	public static final double CLAW_ELEVATOR_SPROCKET_DIAMETER = 1.4;
	public static final double ANTISWAG = 0.6;
	
	public static final double[] CLAW_ELEVATOR_UP_TRAVEL_DISTANCES = {
		11.0,
		7.5,
		15.5
	};
	
	public static final double[] CLAW_ELEVATOR_DOWN_TRAVEL_DISTANCE = {
		15.0,
		7.5,
		11.0
	};
	
	/*
	 * Drivetrain
	 */
	public static final double TANK_STICK_TOLERANCE = .25;
	public static final boolean DRIVETRAIN_USE_LEFT_ENCODER = true;	// Variable to determine which side the encoder is on
	public static final boolean DRIVE_USE_SQUARED_INPUT = false;
	public static final double DRIVETRAIN_WHEEL_DIAMETER = 6.0;
	public static final double DRIVETRAIN_ENCODER_PULSES_PER_REV = 256.0;
	
	// PID Tuning parameters
	public static final double Kp = 0.0;		// Proportional gain
	public static final double Ki = 0.0;		// Integral gain
	public static final double Kd = 0.0;		// Derivative gain

	public static final double KpForward = 0.1;
	public static final double KiForward = 0.01;
	public static final double KdForward = 0.005;

	public static final double KpBackward = 0.1;
	public static final double KiBackward = 0.02;
	public static final double KdBackward = 0.005;
	
	public static final double pid_tolerance = 1;
	
	/*
	 * Vision Processor
	 */
}
