package org.team708.robot;


import edu.wpi.first.wpilibj.buttons.*;
import org.team708.robot.commands.claw.*;
import org.team708.robot.commands.clawElevator.*;
import org.team708.robot.commands.drivetrain.*;
//import org.team708.robot.commands.hockeyStick.*;
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
	private static final int TOGGLE_BRAKE_MODE_BUTTON = Gamepad.button_B;
	private static final int SCORE_TOTE_BUTTON = Gamepad.button_A;
//	private static final int HOLD_FOR_NO_PID_BUTTON = Gamepad.button_R_Shoulder;
	
	// Hockey Stick Buttons
	private static final int TOGGLE_HOCKEY_STICK_BUTTON = Gamepad.button_Y;
	
	// Intake Buttons
//	private static final int toggleIntakePowerButton = Gamepad.button_L_Shoulder;
//	private static final int toggleIntakeDirectionButton = Gamepad.button_R_Shoulder;
	
	/*
	 * Operator Button Assignment
	 */
	// Indexer Buttons
	private static final int TOTE_AXIS = Gamepad.leftStick_Y;
	
	// Claw Buttons
	public static final int TOGGLE_CLAW_OPEN_BUTTON = Gamepad.button_R_Shoulder;
	public static final int TOGGLE_WRIST_POSITION_BUTTON = Gamepad.button_L_Shoulder;
	
	// Claw Elevator Buttons
	public static final int clawHeightIncrementButton = Gamepad.button_B;
	public static final int clawHeightDecrementButton = Gamepad.button_A;
	
	/*
	 * Driver Button Commands
	 */
	private static final Button toggleBrakeMode = new JoystickButton(driverGamepad, TOGGLE_BRAKE_MODE_BUTTON);				// Toggles whether the drive is in all brake or all coast
	public static final Button toggleHockeyStick = new JoystickButton(driverGamepad, TOGGLE_HOCKEY_STICK_BUTTON);			// Toggles the hockey stick
//	public static final Button holdForNoPID = new JoystickButton(driverGamepad, HOLD_FOR_NO_PID_BUTTON);
//	public static final Button toggleIntakePower = new JoystickButton(driverGamepad, toggleIntakePowerButton);			// Toggles the intake power on/off
//	public static final Button toggleIntakeDirection = new JoystickButton(driverGamepad, toggleIntakeDirectionButton);	// Toggles the intake direction
	public static final Button scoreTote = new JoystickButton(driverGamepad, SCORE_TOTE_BUTTON);                          // Raises Totes up to place on step and/or plateform
	/*
	 * Operator Button Commands
	 */
	private static final AxisUp toteUp = new AxisUp(operatorGamepad, TOTE_AXIS);										// Increments one tote height up, picking up a tote along the way
	private static final AxisDown toteDown = new AxisDown(operatorGamepad, TOTE_AXIS);								// Moves the indexer down to release the tote stack
	public static final Button toggleClawOpen = new JoystickButton(operatorGamepad, TOGGLE_CLAW_OPEN_BUTTON);				// Opens and closes the claw on a toggle
	public static final Button toggleWristPosition = new JoystickButton(operatorGamepad, TOGGLE_WRIST_POSITION_BUTTON);	// Toggles the wrist position (horizontal/vertical)
	public static final Button clawHeightIncrement = new JoystickButton(operatorGamepad, clawHeightIncrementButton);	// Increases the claw height by the height of a tote
	public static final Button clawHeightDecrement = new JoystickButton(operatorGamepad, clawHeightDecrementButton);	// Decreases the claw height by the height of a tote
	
	/**
	 * Constructor
	 * Assigns commands to be called when each button is pressed.
	 */
	public OI() {
		/*
		 * Driver Commands to be called by button
		 */
		toggleBrakeMode.whenPressed(new ToggleBrakeMode());
//		toggleHockeyStick.whenPressed(new ToggleHockeyStick());
//		holdForNoPID.whileHeld(new HoldDisablePID());
//		toggleIntakePower.whenPressed(new TogglePower());
//		toggleIntakeDirection.whenPressed(new ToggleDirection());
		scoreTote.whenPressed(new ScoreTote());
		
		/*
		 * Operator Commands to be called by button
		 */
		toggleClawOpen.whenPressed(new ToggleClawOpen());
		toggleWristPosition.whenPressed(new ToggleWrist());
		toteUp.whenActive(new IndexerUp(false));
		toteDown.whenActive(new IndexerDown());
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
