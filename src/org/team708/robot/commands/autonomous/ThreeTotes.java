//package org.team708.robot.commands.autonomous;
//
//import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
//import org.team708.robot.commands.drivetrain.TurnToDegrees;
//
//import edu.wpi.first.wpilibj.command.CommandGroup;
//
///**
// *
// */
//public class ThreeTotes extends CommandGroup {
//	
//	private final double TURN_SPEED = 0.8;
//	private final double TURN_ANGLE = 90.0;
//	
//	private final double DRIVE_DISTANCE = 36.0;
//    
//    public  ThreeTotes() {
//    	addSequential(new OneContainerOneTote());
//    	addSequential(new OneContainerOneTote());
//    	addSequential(new OneContainerOneTote());
//    	addSequential(new TurnToDegrees(TURN_SPEED, TURN_ANGLE));
//    	addSequential(new DriveStraightToEncoderDistance(DRIVE_DISTANCE));
//    	
//        // Add Commands here:
//        // e.g. addSequential(new Command1());
//        //      addSequential(new Command2());
//        // these will run in order.
//
//        // To run multiple commands at the same time,
//        // use addParallel()
//        // e.g. addParallel(new Command1());
//        //      addSequential(new Command2());
//        // Command1 and Command2 will run in parallel.
//
//        // A command group will require all of the subsystems that each member
//        // would require.
//        // e.g. if Command1 requires chassis, and Command2 requires arm,
//        // a CommandGroup containing them would require both the chassis and the
//        // arm.
//    }
//}
