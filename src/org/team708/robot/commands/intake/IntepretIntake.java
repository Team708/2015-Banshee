//package org.team708.robot.commands.intake;
//
//import org.team708.robot.Constants;
//import org.team708.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class IntepretIntake extends Command {
//
//    public IntepretIntake() {
//        // Use requires() here to declare subsystem dependencies
//        requires(Robot.intake);
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	if (Robot.intake.isOn() && !(Robot.drivetrain.getIRDistance() <= Constants.IR_HAS_TOTE_DISTANCE)) {
//			if (Robot.intake.isIn()) {
//				Robot.intake.set(Constants.MOTOR_FORWARD);
//			} else {
//				Robot.intake.set(Constants.MOTOR_REVERSE);
//			}
//    	} else {
//    		Robot.intake.set(0.0);
//    	}
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
