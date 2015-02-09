package org.team708.robot;


import org.team708.robot.commands.claw.ToggleClawOpen;
import org.team708.robot.commands.claw.ToggleWrist;
import org.team708.robot.commands.clawElevator.DecrementClawOne;
import org.team708.robot.commands.clawElevator.IncrementClawOne;
import org.team708.robot.commands.drivetrain.ToggleBrakeMode;
import org.team708.robot.commands.hockeyStick.ToggleHockeyStick;
import org.team708.robot.commands.intake.ToggleDirection;
import org.team708.robot.commands.intake.TogglePower;
import org.team708.robot.commands.toteElevator.ToteElevatorDown;
import org.team708.robot.commands.toteElevator.ToteElevatorUp;
import org.team708.robot.util.Gamepad;
import org.team708.robot.util.triggers.AxisDown;
import org.team708.robot.util.triggers.AxisUp;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	// Gamepads
	public static Gamepad driverGamepad = new Gamepad(RobotMap.driverGamepad);
	public static Gamepad operatorGamepad = new Gamepad(RobotMap.operatorGamepad);
	
	/*
	 * Driver Button Assignment
	 */
	private static final int toggleBrakeModeButton = Gamepad.button_L_Shoulder;
	private static final int toggleHockeyStickButton = Gamepad.button_Y;
	private static final int toggleIntakePowerButton = Gamepad.button_L_Shoulder;
	private static final int toggleIntakeDirectionButton = Gamepad.button_R_Shoulder;
	
	/*
	 * Operator Button Assignment
	 */
	private static final int toteUpAxis = Gamepad.leftStick_Y;
	private static final int toteDownAxis = Gamepad.leftStick_Y;

	public static final int toggleClawOpenButton = Gamepad.button_R_Shoulder;
	public static final int toggleWristPositionButton = Gamepad.button_L_Shoulder;
	public static final int clawHeightIncrementButton = Gamepad.button_Y;
	public static final int clawHeightDecrementButton = Gamepad.button_A;
	
	/*
	 * Driver Button Commands
	 */
	private static final Button toggleBrakeMode = new JoystickButton(driverGamepad, toggleBrakeModeButton);
	public static final Button toggleHockeyStick = new JoystickButton(driverGamepad, toggleHockeyStickButton);			// Toggles the hockey stick
	public static final Button toggleIntakePower = new JoystickButton(driverGamepad, toggleIntakePowerButton);			// Toggles the intake power on/off
	public static final Button toggleIntakeDirection = new JoystickButton(driverGamepad, toggleIntakeDirectionButton);	// Toggles the intake direction
	
	/*
	 * Operator Button Commands
	 */
	private static final AxisUp toteUp = new AxisUp(operatorGamepad, toteUpAxis);
	private static final AxisDown toteDown = new AxisDown(operatorGamepad, toteDownAxis);
	public static final Button toggleClawOpen = new JoystickButton(operatorGamepad, toggleClawOpenButton);				// Opens and closes the claw on a toggle
	public static final Button toggleWristPosition = new JoystickButton(operatorGamepad, toggleWristPositionButton);	// Toggles the wrist position (horizontal/vertical)
	public static final Button clawHeightIncrement = new JoystickButton(operatorGamepad, clawHeightIncrementButton);	// Increases the claw height by the height of a tote
	public static final Button clawHeightDecrement = new JoystickButton(operatorGamepad, clawHeightDecrementButton);	// Decreases the claw height by the height of a tote
	
	/**
	 * Constructor
	 */
	public OI() {
		/*
		 * Driver Commands to be called by button
		 */
		toggleBrakeMode.whenPressed(new ToggleBrakeMode());
		toggleHockeyStick.whenPressed(new ToggleHockeyStick());
		toggleIntakePower.whenPressed(new TogglePower());
		toggleIntakeDirection.whenPressed(new ToggleDirection());
		
		/*
		 * Operator Commands to be called by button
		 */
		toggleClawOpen.whenPressed(new ToggleClawOpen());
		toggleWristPosition.whenPressed(new ToggleWrist());
		toteUp.whenActive(new ToteElevatorUp());
		toteDown.whenActive(new ToteElevatorDown());
		clawHeightIncrement.whenPressed(new IncrementClawOne());
		clawHeightDecrement.whenPressed(new DecrementClawOne());
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

