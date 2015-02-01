package org.team708.robot.subsystems;

import org.team708.robot.RobotMap;
import org.team708.robot.commands.claw.ClawMotorControl;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A claw that picks up recycling containers with a motor, 
 * two arms that open and close off of a single solenoid
 * @param <OFF>
 */
public class Claw<OFF> extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// Limit switch for the intake motors to stop
	
	// Double solenoids to control pistons
	private DoubleSolenoid clawFingerSolenoid;
	private DoubleSolenoid clawWristSolenoid;
	
	// Finger solenoid states
	private static final DoubleSolenoid.Value OPEN = DoubleSolenoid.Value.kReverse;
	private static final DoubleSolenoid.Value CLOSED = DoubleSolenoid.Value.kForward;
	
	// Wrist solenoid states
	private static final DoubleSolenoid.Value VERTICAL = DoubleSolenoid.Value.kReverse;
	private static final DoubleSolenoid.Value HORIZONTAL = DoubleSolenoid.Value.kForward;
	
	// Spike to move the wheels at the end of the claw
	private Relay clawFingerSpike;
	
	// Finger spike states
	private static final Relay.Value INTAKE = Relay.Value.kForward;
	private static final Relay.Value DISPENSE = Relay.Value.kReverse;
	private static final Relay.Value OFF = Relay.Value.kOff;
	
	public Claw() {
		
		// Makes the solenoids
		clawFingerSolenoid = new DoubleSolenoid(RobotMap.clawDoubleSolenoidA, RobotMap.clawDoubleSolenoidB);
		clawWristSolenoid = new DoubleSolenoid(RobotMap.clawWristDoubleSolenoidA, RobotMap.clawWristDoubleSolenoidB);
		
		// Sets solenoids to initial positions
		clawFingerSolenoid.set(OPEN);
		clawWristSolenoid.set(HORIZONTAL);
	
		// Makes the spike for the claw fingers
		clawFingerSpike = new Relay(RobotMap.clawFingerMotorSpike);
		
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ClawMotorControl());
    }
    
    
    // Self explanatory getter/setter methods.
    
    public void openClaw() {
    	
    	clawFingerSolenoid.set(OPEN);
    
    }
    
    public void closeClaw() {
    	
    	clawFingerSolenoid.set(CLOSED);
    	
    }
    
    public boolean isClawOpen() {
    	
    	return clawFingerSolenoid.get().equals(OPEN);
    
    }
    
    public boolean isClawClosed() {
    	return clawFingerSolenoid.get().equals(CLOSED);
    }
    
    public void setClawVertical() {
    	
    	clawWristSolenoid.set(VERTICAL);
    
    }
    
    public void setClawHorizontal() {
    	
    	clawWristSolenoid.set(HORIZONTAL);
    	
    }
    
    public boolean isClawVertical() {
    	
    	return clawWristSolenoid.get().equals(VERTICAL);
    
    }
    
    public boolean isClawHorizontal() {
    	return clawWristSolenoid.get().equals(HORIZONTAL);
    }
    
    public void intake() {
    	
    	clawFingerSpike.set(INTAKE);
    
    }
    
    public void dispense() {
    	
    	clawFingerSpike.set(DISPENSE);
    	
    }
    
    public void stopFingerMotor() {
    	
    	clawFingerSpike.set(OFF);
    	
    }
    
}

