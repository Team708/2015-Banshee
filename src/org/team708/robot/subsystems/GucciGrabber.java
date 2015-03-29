package org.team708.robot.subsystems;

import org.team708.robot.Constants;
import org.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GucciGrabber extends Subsystem {
    
    private CANTalon gucciGrabberMotor;
    
    private boolean deployed = false;
    
    public GucciGrabber() {
    	gucciGrabberMotor = new CANTalon(RobotMap.gucciGrabberMotor);
    }
    
    public void set(double speed) {
    	gucciGrabberMotor.set(speed);
    }
    
    public void deploy() {
    	set(Constants.MOTOR_FORWARD);
    	deployed = true;
    }
    
    public void retract() {
    	set(Constants.MOTOR_REVERSE);
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