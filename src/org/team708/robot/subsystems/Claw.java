package org.team708.robot.subsystems;

import org.team708.robot.RobotMap;
import org.team708.robot.commands.claw.ClawMotorControl;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A claw that picks up recycling containers with a motor, 
 * two arms that open and close off of a single solenoid
 */
public class Claw extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// Limit switch for the intake motors to stop
	
	// Double solenoids to control pistons
	private DoubleSolenoid clawFingerSolenoid;
	private DoubleSolenoid clawWristSolenoid;

	// Spike to move the wheels at the end of the claw
	private Relay clawFingerSpike;
	
	public Claw() {
		
		// Makes the solenoids
		clawFingerSolenoid = new DoubleSolenoid(RobotMap.clawDoubleSolenoidA, RobotMap.clawDoubleSolenoidB);
		clawWristSolenoid = new DoubleSolenoid(RobotMap.clawWristDoubleSolenoidA, RobotMap.clawWristDoubleSolenoidB);
		
		// Sets solenoids to initial positions
		clawFingerSolenoid.set(DoubleSolenoid.Value.kForward);
		clawWristSolenoid.set(DoubleSolenoid.Value.kReverse);
	
		// Makes the spike for the claw fingers
		clawFingerSpike = new Relay(RobotMap.clawFingerMotorSpike);
		
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ClawMotorControl());
    }
    
    public void setClawOpen(DoubleSolenoid.Value value) {
    	
    	clawFingerSolenoid.set(value);
    
    }
    
    public DoubleSolenoid.Value getClawOpen() {
    	
    	return clawFingerSolenoid.get();
    
    }
    
    public void setClawPosition(DoubleSolenoid.Value value) {
    	
    	clawWristSolenoid.set(value);
    
    }
    
    public DoubleSolenoid.Value getClawPosition() {
    	
    	return clawWristSolenoid.get();
    
    }
    
    public void setFingerMotor(Relay.Value value) {
    	
    	clawFingerSpike.set(value);
    
    }
    
    public void stopFingerMotor() {
    	
    	clawFingerSpike.set(Relay.Value.kOff);
    	
    }
    
}

