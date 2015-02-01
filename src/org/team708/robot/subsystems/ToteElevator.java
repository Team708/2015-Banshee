package org.team708.robot.subsystems;

import org.team708.robot.RobotMap;
import org.team708.robot.util.IRSensor;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ToteElevator extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//Motor controllers
	private CANTalon toteElevatorMotor1; 
	private CANTalon toteElevatorMotor2;
	public final double RAISE_SPEED = 1.0;
	public final double LOWER_SPEED = -1.0;
	
	public int TOTE_COUNT = 0;
	public final int TOTE_UPPER_LIMIT = 3;
	public final int TOTE_LOWER_LIMIT = 0;
	
	//Digital sensors
	private Encoder toteElevatorEncoder;
	private final double PULSES_PER_REVOLUTION = 7.0;
	private final double GEARING = (16 / 50);
	private double DISTANCE_PER_PULSE;
	
	public final double TOP_ENCODER_DISTANCE = 25.0;
	public final double BOTTOM_ENCODER_DISTANCE = 0.0;
	
	//Analog sensors
	private IRSensor toteElevatorIRSensor;
	private final double HAS_TOTE_DISTANCE_CLOSE = 0;
	private final double HAS_TOTE_DISTANCE_FAR = 3;
	
	public ToteElevator() {
		//Creates motors that run elevator
		toteElevatorMotor1 = new CANTalon(RobotMap.toteElevatorMotor1);
		toteElevatorMotor2 = new CANTalon(RobotMap.toteElevatorMotor2);
		
		//Creates encoders for elevator motors
		toteElevatorEncoder = new Encoder(RobotMap.toteElevatorEncoderA, RobotMap.toteElevatorEncoderB);
		DISTANCE_PER_PULSE = PULSES_PER_REVOLUTION * GEARING;
		toteElevatorEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
		toteElevatorEncoder.setReverseDirection(true);
		
		//Creates IR sensor
		toteElevatorIRSensor = new IRSensor(RobotMap.toteElevatorIRSensor, IRSensor.GP2Y0A21YK0F);
		toteElevatorIRSensor.setTriggerBounds(HAS_TOTE_DISTANCE_CLOSE, HAS_TOTE_DISTANCE_FAR, false);

	}
	
	public void set(double speed) {
		toteElevatorMotor1.set(speed);
		toteElevatorMotor2.set(-speed);
	}
	
	public double getEncoderDistance() {
		return toteElevatorEncoder.getDistance();
	}
	
	public double getEncoderRate() {
		return toteElevatorEncoder.getRate();
	}
	
	public void resetEncoder() {
		toteElevatorEncoder.reset();
	}
	/*
	 * Checks if tote is at height yet
	 */
	public boolean encoderTop() {
		if (getEncoderDistance() < TOP_ENCODER_DISTANCE) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean encoderBottom() {
		if (getEncoderDistance() > BOTTOM_ENCODER_DISTANCE) {
			return false;
		} else {
			return true;
		}
}
	/*
	 * Checks if tote is in elevator
	 */
	public boolean hasTote() {
		return toteElevatorIRSensor.isTriggered();
	}
	/*
	 * Raises the tote
	 */
	public void raiseTote() {
		this.set(RAISE_SPEED);

	}
	
	/*
	 * Lowers the tote
	 */
	public void lowerTote() {
		this.set(LOWER_SPEED);
	}
	
	public void stopTote() {
		this.set(0.0);
	}
	
	public void increaseCount() {
		TOTE_COUNT = TOTE_COUNT + 1;
	}
	
	public void decreaseCount() {
		TOTE_COUNT = TOTE_COUNT - 1;
	}

	//raises tote when y is pressed
	public void YToteUp() {
		if (TOTE_COUNT < TOTE_UPPER_LIMIT) {
			if (!encoderTop()) {
				this.raiseTote();
			} else {
				this.increaseCount();
				this.stopTote();
				this.resetEncoder();
			}
		}
		else {
			this.stopTote();
			}
	}
	//lowers tote when a is pressed	
	public void AToteDown() {
			if (TOTE_COUNT > TOTE_LOWER_LIMIT) {
				if (!encoderBottom()) {
					this.lowerTote();
				} 
				else {
					this.decreaseCount();
					this.stopTote();
					this.resetEncoder();
				}
		} else {
			this.stopTote();
			}
		}
		
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

