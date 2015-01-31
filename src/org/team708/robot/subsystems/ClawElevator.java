package org.team708.robot.subsystems;

import org.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * 
 */
public class ClawElevator extends PIDSubsystem {

	// Sets PID variables
	private static double Kp = 0.0;
	private static double Ki = 0.0;
	private static double Kd = 0.0;
	
	// Makes encoder
	private Encoder clawElevatorEncoder;
	
	//Makes motor
	private CANTalon clawElevatorMotor;
	
    // Initialize your subsystem here
    public ClawElevator() {
    	super(Kp, Ki, Kd);
        
    	// Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	
    	clawElevatorEncoder = new Encoder(RobotMap.clawElevatorEncoderA, RobotMap.clawElevatorEncoderB);
    	clawElevatorMotor = new CANTalon(RobotMap.clawElevatorMotor);
    	
    	setSetpoint(0.0);
    	enable();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return clawElevatorEncoder.getDistance();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	clawElevatorMotor.set(output);
    }
}
