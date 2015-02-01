package org.team708.robot;

import org.team708.robot.commands.claw.ClawHeightDecrement;
import org.team708.robot.commands.claw.ClawHeightIncrement;
import org.team708.robot.commands.claw.ToggleClawOpen;
import org.team708.robot.commands.claw.ToggleWrist;
import org.team708.robot.util.Gamepad;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	// Button IDs
	public static final int toggleClawOpenButton = Gamepad.button_R_Shoulder;
	public static final int toggleWristPositionButton = Gamepad.button_L_Shoulder;
	public static final int clawHeightIncrementButton = Gamepad.button_Y;
	public static final int clawHeightDecrementButton = Gamepad.button_A;
	
	// Gamepad IDs
	public static final Gamepad driverGamepad = new Gamepad(RobotMap.driverGamepad);
	public static final Gamepad operatorGamepad = new Gamepad(RobotMap.operatorGamepad);
	
	// Opens and closes the claw on a toggle
	Button toggleClawOpen = new JoystickButton(operatorGamepad, toggleClawOpenButton);
	
	// Toggles the wrist position (horizontal/vertical)
	Button toggleWristPosition = new JoystickButton(operatorGamepad, toggleWristPositionButton);
	
	// Increases the claw height by the height of a tote
	Button clawHeightIncrement = new JoystickButton(operatorGamepad, clawHeightIncrementButton);
	
	// Decreases the claw height by the height of a tote
	Button clawHeightDecrement = new JoystickButton(operatorGamepad, clawHeightDecrementButton);
	
	public OI() {
		
		// Maps the buttons to commands
		toggleClawOpen.whenPressed(new ToggleClawOpen());
		toggleWristPosition.whenPressed(new ToggleWrist());
		clawHeightIncrement.whenPressed(new ClawHeightIncrement());
		clawHeightDecrement.whenPressed(new ClawHeightDecrement());
		
	}
	
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

