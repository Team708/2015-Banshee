package org.team708.robot.util.triggers;

import org.team708.robot.util.Gamepad;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * Makes moving an axis down on a joystick result in a button input
 */
public class AxisDown extends Trigger {
	 
	private Gamepad gamepad;
	private int axis;
		
	public AxisDown(Gamepad targetGamepad, int targetAxis) {
		gamepad = targetGamepad;
		axis = targetAxis;
	}
		
    public boolean get() {
        return (gamepad.getAxis(axis) <= -.50);
    }
}
