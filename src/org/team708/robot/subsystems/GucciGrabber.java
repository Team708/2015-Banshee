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
    
    private boolean deployed = false;
    
    public GucciGrabber() {
    	gucciGrabberSolenoid = new DoubleSolenoid(RobotMap.gucciGrabberSolenoidA, RobotMap.gucciGrabberSolenoidB);
    }
    
    public void deploy() {
    	gucciGrabberSolenoid.set(Constants.DEPLOYED);
    	deployed = true;
    }
    
    public void retract() {
    	gucciGrabberSolenoid.set(Constants.RETRACTED);
    	deployed = false;
    }
    
    public boolean isDeployed() {
    	return deployed;
    }
    
    public void setDeployed(boolean deployed) {
    	this.deployed = deployed;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void sendToDashboard() {
    	SmartDashboard.putBoolean("Gucci Grabber Deployed", isDeployed());
    }
}