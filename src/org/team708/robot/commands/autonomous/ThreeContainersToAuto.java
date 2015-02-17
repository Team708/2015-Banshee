//package org.team708.robot.commands.autonomous;
//
//import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
//
//import edu.wpi.first.wpilibj.command.CommandGroup;
//
///**
// *
// */
//public class ThreeContainersToAuto extends CommandGroup {
//    
//	private final double DRIVE_DISTANCE = 71.0;
//	
//    public  ThreeContainersToAuto() {
//    	
//    	addSequential(new DealWithContainer());
//    	addSequential(new DriveStraightToEncoderDistance(DRIVE_DISTANCE));
//    	addSequential(new DealWithContainer());
//    	addSequential(new DriveStraightToEncoderDistance(DRIVE_DISTANCE));
//    	addSequential(new DealWithContainer());
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
