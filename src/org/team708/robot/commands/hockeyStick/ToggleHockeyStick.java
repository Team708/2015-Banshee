//package org.team708.robot.commands.hockeyStick;
//
//import org.team708.robot.Constants;
//import org.team708.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// * Hockey stick command to toggle if it is in or out.
// * @author frakerman1
// * @author omn0mn0m
// */
//public class ToggleHockeyStick extends Command {
//
//	/**
//	 * Constructor
//	 */
//    public ToggleHockeyStick() {
//        // Use requires() here to declare subsystem dependencies
//        // eg. requires(chassis);
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	if (Robot.hockeyStick.getSolenoidValue().equals(Constants.DEPLOYED)) {
//    		Robot.hockeyStick.retractHockey();
//    	} else {
//    		Robot.hockeyStick.deployHockey();
//    	}
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return true;
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
