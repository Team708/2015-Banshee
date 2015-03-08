//package org.team708.robot.commands.claw;
//
//import org.team708.robot.OI;
//import org.team708.robot.Robot;
//import org.team708.robot.util.Gamepad;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class ClawMotorControl extends Command {
//
//    public ClawMotorControl() {
//        // Use requires() here to declare subsystem dependencies
//        // eg. requires(chassis);
//    	requires(Robot.claw);
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	
//    	/* Sets the motor speed to forward if the joystick is held right
//    	 *  
//    	 */
//    	if(OI.operatorGamepad.getAxis(Gamepad.rightStick_X) >= .50) {
//    		Robot.claw.intake();
//    	} else if(OI.operatorGamepad.getAxis(Gamepad.rightStick_X) <= -.50) {
//    		Robot.claw.dispense();
//    	} else {
//    		Robot.claw.stopFingerMotor();
//    	}
//    	
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return false;
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    }
//}
