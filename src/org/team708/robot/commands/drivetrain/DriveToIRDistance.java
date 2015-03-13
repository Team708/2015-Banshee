//package org.team708.robot.commands.drivetrain;
//
//import org.team708.robot.Robot;
//import org.team708.robot.Constants;
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class DriveToIRDistance extends Command {
//	
//	private double targetDistance;
//	private double moveSpeed;
//	private double tolerance;
//	private double minValue;
//	private double maxValue;
//
//	/**
//	 * Constructor
//	 * @param targetDistance
//	 * @param minValue
//	 * @param maxValue
//	 * @param tolerance
//	 */
//    public DriveToIRDistance(double targetDistance, double minValue, double maxValue, double tolerance) {
//        // Use requires() here to declare subsystem dependencies
//        requires(Robot.drivetrain);
//        
//        this.targetDistance = targetDistance;
//        this.tolerance = tolerance;
//        this.minValue = minValue;
//        this.maxValue = maxValue;
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	moveSpeed = Robot.drivetrain.moveByIR(targetDistance, minValue, maxValue, tolerance);
//    	Robot.drivetrain.haloDrive(moveSpeed, 0.0, false);
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return (moveSpeed == 0.0);
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    	Robot.drivetrain.stop();
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    	end();
//    }
//}
