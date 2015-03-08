package org.team708.robot.commands.autonomous;
//package org.team708.robot.commands.autonomous;
//
//import org.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
//import org.team708.robot.commands.hockeyStick.ToggleHockeyStick;
//
//import edu.wpi.first.wpilibj.command.CommandGroup;
//
///**
// *
// */
//public class HockeyStickClearToAutoZone extends CommandGroup {
//    
//	private final double DRIVE_TO_CLEAR_DISTANCE = 108;
//	
//    public  HockeyStickClearToAutoZone() {
//    	
//    	addSequential(new ToggleHockeyStick());
//    	// Run hockey stick motor
//    	addSequential(new DriveStraightToEncoderDistance(DRIVE_TO_CLEAR_DISTANCE));
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
