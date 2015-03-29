package org.team708.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GucciGrabber extends Subsystem {
    
    private CANTalon gucciGrabberLeft, gucciGrabberRight;
    
    public GucciGrabber() {
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}