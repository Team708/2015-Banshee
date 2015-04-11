package org.team708.robot;


import edu.wpi.first.wpilibj.buttons.*;

import org.team708.robot.commands.claw.*;
import org.team708.robot.commands.clawElevator.*;
import org.team708.robot.commands.drivetrain.*;
import org.team708.robot.commands.gucciGrabber.ToggleGucciGrabber;
import org.team708.robot.commands.indexer.*;
import org.team708.robot.util.*;
import org.team708.robot.util.triggers.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * @author omn0mn0m
 * @author kazekitteh
 * @author jlwang
 * @author frakerman1
 */
public class OI {
	
	// Gamepads
	public final static Gamepad driverGamepad = new Gamepad(RobotMap.driverGamepad);			// Driver gamepad
	public final static Gamepad operatorGamepad = new Gamepad(RobotMap.operatorGamepad);		// Operator gamepad
	
	/*
	 * Driver Button Assignment
	 */
	// Drivetrain Buttons
	private static final int ADJUST_DOWN_BUTTON = Gamepad.button_B;
	private static final int ADJUST_UP_BUTTON = Gamepad.button_A;
//	private static final int HOLD_FOR_NO_PID_BUTTON = Gamepad.button_R_Shoulder;
	
	/*
	 * Operator Button Assignment
	 */
	// Indexer Buttons
	private static final int TOTE_AXIS = Gamepad.leftStick_Y;
	
	// Claw Buttons
	public static final int TOGGLE_CLAW_OPEN_BUTTON = Gamepad.button_R_Shoulder;
//	public static final int INTERRUPT_CLAW_BUTTON = Gamepad.button_L_Shoulder;
//	public static final int ANTI_SWAG_CLAW_BUTTON = Gamepad.button_L_Shoulder;
	public static final int INDEXER_MANUAL_OVERRIDE_BUTTON = Gamepad.button_L_Shoulder;
	
	// Claw Elevator Buttons
	public static final int clawHeightIncrementButton = Gamepad.button_B;
	public static final int clawHeightDecrementButton = Gamepad.button_A;
	
	// Gucci Grabber Buttons
	public static final int GUCCI_GRABBER_TOGGLE_BUTTON = Gamepad.button_Y;
	
	/*
	 * Driver Button Commands
	 */
	private static final Button adjustDown = new JoystickButton(driverGamepad, ADJUST_DOWN_BUTTON);						// Toggles whether the drive is in all brake or all coast
//	public static final Button holdForNoPID = new JoystickButton(driverGamepad, HOLD_FOR_NO_PID_BUTTON);
	public static final Button adjustUp = new JoystickButton(driverGamepad, ADJUST_UP_BUTTON);                          // Raises Totes up to place on step and/or plateform
	/*
	 * Operator Button Commands
	 */
	private static final AxisUp toteUp = new AxisUp(operatorGamepad, TOTE_AXIS);										// Increments one tote height up, picking up a tote along the way
	private static final AxisDown toteDown = new AxisDown(operatorGamepad, TOTE_AXIS);									// Moves the indexer down to release the tote stack
	public static final Button toggleClawOpen = new JoystickButton(operatorGamepad, TOGGLE_CLAW_OPEN_BUTTON);			// Opens and closes the claw on a toggle
//	public static final Button interruptClaw = new JoystickButton(operatorGamepad, INTERRUPT_CLAW_BUTTON);				// Toggles the wrist position (horizontal/vertical)
//	public static final Button antiSwagClaw = new JoystickButton(operatorGamepad, ANTI_SWAG_CLAW_BUTTON);
	public static final Button indexerManualOverride = new JoystickButton(operatorGamepad, INDEXER_MANUAL_OVERRIDE_BUTTON);
	public static final Button clawHeightIncrement = new JoystickButton(operatorGamepad, clawHeightIncrementButton);	// Increases the claw height by the height of a tote
	public static final Button clawHeightDecrement = new JoystickButton(operatorGamepad, clawHeightDecrementButton);	// Decreases the claw height by the height of a tote
	public static final Button gucciGrabberToggle = new JoystickButton(operatorGamepad, GUCCI_GRABBER_TOGGLE_BUTTON);	// Toggles the position of the gucci grabber
	
	/**
	 * Constructor
	 * Assigns commands to be called when each button is pressed.
	 */
	public OI() {
		/*
		 * Driver Commands to be called by button
		 */
		adjustDown.whenPressed(new AdjustIndexer(false));
//		holdForNoPID.whileHeld(new HoldDisablePID());
		adjustUp.whenPressed(new AdjustIndexer(true));
		
		/*
		 * Operator Commands to be called by button
		 */
		toggleClawOpen.whenPressed(new ToggleClawOpen());
//		interruptClaw.whenPressed(new StopClawElevator());
		toteUp.whenActive(new IndexerUp(false));
		toteDown.whenActive(new IndexerDown());
//		clawHeightIncrement.whenPressed(new HoldClawMove(true));
//		clawHeightDecrement.whenPressed(new HoldClawMove(false));
		gucciGrabberToggle.whenPressed(new ToggleGucciGrabber());
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
