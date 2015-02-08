package org.team708.robot.subsystems;

import org.team708.robot.RobotMap;
import org.team708.robot.commands.claw.ClawMotorControl;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A claw that picks up recycling containers with a motor, 
 * two arms that open and close off of a single solenoid, and
 * a rotary piston which controls the orientation of the arm
 */
public class Claw extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// Limit switch for the intake motors to stop
	
	// Double solenoids to control pistons
	private DoubleSolenoid clawFingerSolenoid;	// Pistons that open and close the claw
	private DoubleSolenoid clawWristSolenoid;	// Rotary piston that changes the orientation of the claw
	
	// Finger solenoid states
	private static final DoubleSolenoid.Value OPEN = DoubleSolenoid.Value.kReverse;
	private static final DoubleSolenoid.Value CLOSED = DoubleSolenoid.Value.kForward;
	
	// Wrist solenoid states
	private static final DoubleSolenoid.Value VERTICAL = DoubleSolenoid.Value.kReverse;
	private static final DoubleSolenoid.Value HORIZONTAL = DoubleSolenoid.Value.kForward;
	
	// Talon controlled motor to move the bands along the fingers of the claw
	private CANTalon clawFingerMotor;
	
	// Finger states (FWD/BACK/OFF)
	private static final double INTAKE_SPEED = 1.0;
	private static final double DISPENSE_SPEED = -1.0;
	private static final double OFF_SPEED = 0.0;
	
	public Claw() {
		
		// Makes the solenoids
		clawFingerSolenoid = new DoubleSolenoid(RobotMap.clawDoubleSolenoidA, RobotMap.clawDoubleSolenoidB);
		clawWristSolenoid = new DoubleSolenoid(RobotMap.clawWristDoubleSolenoidA, RobotMap.clawWristDoubleSolenoidB);
		
		// Sets solenoids to initial positions
		clawFingerSolenoid.set(OPEN);
		clawWristSolenoid.set(HORIZONTAL);
	
		// Makes the spike for the claw fingers
		clawFingerMotor = new CANTalon(RobotMap.clawFingerMotorSpike);
		
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ClawMotorControl());
    }
    
    /**
     * Opens the claw by setting the solenoid value
     */
    public void openClaw() {
    	
    	clawFingerSolenoid.set(OPEN);
    
    }
    
    /**
     * Closes the claw by setting the solenoid value
     */
    public void closeClaw() {
    	
    	clawFingerSolenoid.set(CLOSED);
    	
    }
    
    /**
     * Returns true if the claw is open
     * @return Claw state as boolean
     */
    public boolean isClawOpen() {
    	
    	return clawFingerSolenoid.get().equals(OPEN);
    
    }
    
    /**
     * Returns true if the claw is closed
     * @return Claw state as boolean
     */
    public boolean isClawClosed() {
    	return clawFingerSolenoid.get().equals(CLOSED);
    }
    
    /**
     * Makes the claw fingers perpendicular to the ground by setting the solenoid value
     */
    public void setClawVertical() {
    	
    	clawWristSolenoid.set(VERTICAL);
    
    }
    
    /**
     * Makes the claw fingers parallel to the ground by setting the solenoid value
     */
    public void setClawHorizontal() {
    	
    	clawWristSolenoid.set(HORIZONTAL);
    	
    }
    
    /**
     * Returns true if the claw is vertical
     * @return Claw orientation
     */
    public boolean isClawVertical() {
    	
    	return clawWristSolenoid.get().equals(VERTICAL);
    
    }
    
    /**
     * Returns true if the claw is horizontal
     * @return Claw orientation
     */
    public boolean isClawHorizontal() {
    	return clawWristSolenoid.get().equals(HORIZONTAL);
    }
    
    /**
     * Sets the motor to intake a container
     */
    public void intake() {
    	
    	clawFingerMotor.set(INTAKE_SPEED);
    
    }
    
    /**
     * Sets the motor to dispense a container
     */
    public void dispense() {
    	
    	clawFingerMotor.set(DISPENSE_SPEED);
    	
    }
    
    /**
     * Stops the motor
     */
    public void stopFingerMotor() {
    	
    	clawFingerMotor.set(OFF_SPEED);
    	
    }
    
}

