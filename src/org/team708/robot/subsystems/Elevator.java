package org.team708.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *Indexes totes
 */
public class Elevator extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//creates motor controllers
	private final SpeedController leftElevatorMotor, rightElevatorMotor;
	
	//creates sensors
	private final Encoder elevatorEncoder;
	private final DigitalInput upperLimit, lowerLimit;
	
	//encoder values for raising tote
	private static final int INDEX_MOVEMENT = 100;
	
	//values for location of tote/height of elevator
	private final int ABOVE_LIMIT = 0; //above upper limit switch
	private final int BETWEEN_LIMIT = 1; //between limit switches
	private final int BELOW_LIMIT = 2; //below lower limit switch
	private final int UNKNOWN = 3; //do not know where the elevator is
	private int currentPosition = UNKNOWN; // sets initial
	
	
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

