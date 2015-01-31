package org.team708.robot.subsystems;

import org.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Claw extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// Double solenoids to control pistons
	private DoubleSolenoid clawSolenoid;
	private DoubleSolenoid clawRotorySolenoid;

	// Spike to move the wheels at the end of the claw
	private Relay clawFingerSpike;
	
	public Claw() {
		
		// Makes the solenoids
		clawSolenoid = new DoubleSolenoid(RobotMap.clawDoubleSolenoidA, RobotMap.clawDoubleSolenoidB);
		clawRotorySolenoid = new DoubleSolenoid(RobotMap.clawWristDoubleSolenoidA, RobotMap.clawWristDoubleSolenoidB);
	
		// Makes the spike for the claw fingers
		clawFingerSpike = new Relay(RobotMap.clawFingerMotorSpike);
		
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
}

