package org.team708.robot.subsystems;

import org.team708.robot.Constants;
import org.team708.robot.RobotMap;
//import org.team708.robot.commands.claw.ClawMotorControl;


//import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A claw that picks up recycling containers with a motor, 
 * two arms that open and close off of a single solenoid, and
 * a rotary piston which controls the orientation of the arm
 * @author katzekitteh
 * @author omn0mn0m
 */
public class Claw extends Subsystem {
	
	private DoubleSolenoid clawFingerSolenoid;	// Pistons that open and close the claw
	private DoubleSolenoid clawWristSolenoid;	// Rotary piston that changes the orientation of the claw
	
//	private CANTalon clawFingerMotor;			// Talon controlled motor to move the bands along the fingers of the claw
	
	/**
	 * Constructor
	 */
	public Claw() {
		// Makes the solenoids
		clawFingerSolenoid = new DoubleSolenoid(RobotMap.clawDoubleSolenoidA, RobotMap.clawDoubleSolenoidB);
		clawWristSolenoid = new DoubleSolenoid(RobotMap.clawWristDoubleSolenoidA, RobotMap.clawWristDoubleSolenoidB);
		
		// Sets solenoids to initial positions
		clawFingerSolenoid.set(Constants.OPEN);
		clawWristSolenoid.set(Constants.HORIZONTAL);
	
//		clawFingerMotor = new CANTalon(RobotMap.clawFingerMotor);		// Makes the spike for the claw fingers
		
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
//    	setDefaultCommand(new ClawMotorControl());
    }
    
    /**
     * Opens the claw by setting the solenoid value
     */
    public void openClaw() {
    	clawFingerSolenoid.set(Constants.OPEN);
    }
    
    /**
     * Closes the claw by setting the solenoid value
     */
    public void closeClaw() {
    	clawFingerSolenoid.set(Constants.CLOSED);
    }
    
    /**
     * Returns true if the claw is open
     * @return Claw state as boolean
     */
    public boolean isClawOpen() {
    	return clawFingerSolenoid.get().equals(Constants.OPEN);
    }
    
    /**
     * Returns true if the claw is closed
     * @return Claw state as boolean
     */
    public boolean isClawClosed() {
    	return clawFingerSolenoid.get().equals(Constants.CLOSED);
    }
    
    /**
     * Makes the claw fingers perpendicular to the ground by setting the solenoid value
     */
    public void setClawVertical() {
    	clawWristSolenoid.set(Constants.VERTICAL);
    }
    
    /**
     * Makes the claw fingers parallel to the ground by setting the solenoid value
     */
    public void setClawHorizontal() {
    	clawWristSolenoid.set(Constants.HORIZONTAL);
    }
    
    /**
     * Returns true if the claw is vertical
     * @return Claw orientation
     */
    public boolean isClawVertical() {
    	return clawWristSolenoid.get().equals(Constants.VERTICAL);
    }
    
    /**
     * Returns true if the claw is horizontal
     * @return Claw orientation
     */
    public boolean isClawHorizontal() {
    	return clawWristSolenoid.get().equals(Constants.HORIZONTAL);
    }
    
    public void sendToDashboard() {
    	SmartDashboard.putBoolean("Claw Open", isClawOpen());
    }
    
//    /**
//     * Sets the motor to intake a container
//     */
//    public void intake() {
//    	clawFingerMotor.set(Constants.MOTOR_FORWARD);
//    }
//    
//    /**
//     * Sets the motor to dispense a container
//     */
//    public void dispense() {
//    	
//    	clawFingerMotor.set(Constants.MOTOR_REVERSE);
//    	
//    }
//    
//    /**
//     * Stops the motor
//     */
//    public void stopFingerMotor() {
//    	clawFingerMotor.set(Constants.MOTOR_OFF);
//    }
}