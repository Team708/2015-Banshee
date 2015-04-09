package org.team708.robot.subsystems;

import org.team708.robot.Constants;
import org.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GucciGrabber extends Subsystem {
    
    private DoubleSolenoid gucciGrabberSolenoid;
    
    public GucciGrabber() {
    	gucciGrabberSolenoid = new DoubleSolenoid(RobotMap.gucciGrabberSolenoidA, RobotMap.gucciGrabberSolenoidB);
    }
    
    public void deploy() {
    	gucciGrabberSolenoid.set(Constants.DEPLOYED);
    }
    
    public void retract() {
    	gucciGrabberSolenoid.set(Constants.RETRACTED);
    }
    
    public boolean isDeployed() {
    	return gucciGrabberSolenoid.get().equals(Constants.DEPLOYED);
    }
    
    public boolean isRetracted() {
    	return gucciGrabberSolenoid.get().equals(Constants.RETRACTED);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void sendToDashboard() {
    	SmartDashboard.putBoolean("Gucci Grabber Deployed", isDeployed());
    }
}