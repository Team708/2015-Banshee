
package org.team708.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.team708.robot.commands.DoNothing;
import org.team708.robot.commands.autonomous.ShoveAll;
import org.team708.robot.commands.autonomous.encoder.CanGrabMoveBack;
import org.team708.robot.commands.autonomous.encoder.CanGrabMoveToNext;
import org.team708.robot.commands.autonomous.encoder.CanGrabNoMove;
//import org.team708.robot.commands.autonomous.ClearStep;
//import org.team708.robot.commands.autonomous.HockeyStickClearToAutoZone;
//import org.team708.robot.commands.autonomous.ThreeContainersToAuto;
//import org.team708.robot.commands.autonomous.ThreeTotes;
import org.team708.robot.commands.autonomous.encoder.ContainerToAutoZone;
import org.team708.robot.commands.autonomous.encoder.ContainerSpinTote;
import org.team708.robot.commands.autonomous.encoder.ContainerToteSpinTote;
import org.team708.robot.commands.autonomous.encoder.ContainerToteTote;
import org.team708.robot.commands.autonomous.encoder.RobotToAutozone;
import org.team708.robot.commands.autonomous.encoder.ThreeTotes;
import org.team708.robot.commands.autonomous.encoder.ToteToAutozone;
import org.team708.robot.commands.autonomous.optical.ContainerToAutoZoneByOptical;
import org.team708.robot.commands.autonomous.optical.ContainerSpinToteByOptical;
import org.team708.robot.commands.autonomous.optical.ContainerToteByOptical;
import org.team708.robot.commands.autonomous.optical.ContainerToteSpinToteByOptical;
import org.team708.robot.commands.autonomous.optical.ContainerToteToteByOptical;
import org.team708.robot.commands.autonomous.optical.RobotToAutozoneByOptical;
import org.team708.robot.commands.autonomous.optical.ToteToAutozoneByOptical;
import org.team708.robot.commands.autonomous.steps.DriveOpticalAndEncoder;
import org.team708.robot.commands.clawElevator.ClawElevatorByEncoder;
//import org.team708.robot.commands.drivetrain.DriveToIRDistance;
//import org.team708.robot.commands.visionProcessor.FollowYellowTote;
import org.team708.robot.subsystems.Drivetrain;
//import org.team708.robot.subsystems.VisionProcessor;
import org.team708.robot.subsystems.Claw;
import org.team708.robot.subsystems.ClawElevator;
import org.team708.robot.subsystems.GucciGrabber;
import org.team708.robot.subsystems.Indexer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 
 * @author omn0mn0m
 */
public class Robot extends IterativeRobot {
    
    Timer statsTimer;										// Timer used for Smart Dash statistics
    
    public static Drivetrain drivetrain;
//	public static VisionProcessor visionProcessor;
	public static Indexer indexer;
	public static Claw claw;
	public static ClawElevator clawElevator;
	public static GucciGrabber gucciGrabber;
	public static OI oi;

    Command autonomousCommand;
    SendableChooser autonomousMode;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        statsTimer = new Timer();	// Initialises the timer for sending Smart Dashboard data
        statsTimer.start();			// Starts the timer for the Smart Dashboard
        // Subsystem Initialisation
        drivetrain = new Drivetrain();
//		visionProcessor = new VisionProcessor();
		indexer = new Indexer();
		claw = new Claw();
		clawElevator = new ClawElevator();
		gucciGrabber = new GucciGrabber();
		
		oi = new OI();	// Initializes the OI. This MUST BE LAST or a NullPointerException will be thrown
		
		sendDashboardSubsystems();	// Sends each subsystem's currently running command to the Smart Dashboard
		
		autonomousMode = new SendableChooser();		// Initializes the Autonomous selection box
		queueAutonomousModes();						// Adds autonomous modes to the selection box
    }
	
    /**
     * Runs periodically while the robot is enabled
     */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		sendStatistics();
	}

	/**
	 * Runs at the start of autonomous mode
	 */
    public void autonomousInit() {
        // schedule the autonomous command (example)
    	autonomousCommand = (Command)autonomousMode.getSelected();
    	indexer.toteCount = 0;
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        sendStatistics();
    }

    /**
     * Runs when teleop mode initialises
     */
    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit() {
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        sendStatistics();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        sendStatistics();
    }
    
    /**
     * Sends data from each subsystem periodically as set by sendStatsIntervalSec
     */
    private void sendStatistics() {
        if (statsTimer.get() >= Constants.SEND_STATS_INTERVAL) {
            statsTimer.reset();

            // Various debug information
            drivetrain.sendToDashboard();
//          visionProcessor.sendToDashboard();
            clawElevator.sendToDashboard();
            indexer.sendToDashboard();
            claw.sendToDashboard();
            gucciGrabber.sendToDashboard();
        }
    }
    
    /**
     * Adds every autonomous mode to the selection box and adds the box to the Smart Dashboard
     */
    private void queueAutonomousModes() {
    	autonomousMode.addObject("1) Do Nothing", new DoNothing());
		autonomousMode.addObject("2) Move to Autozone", new RobotToAutozone());
//		autonomousMode.addDefault("3) One Tote", new ToteToAutozoneByOptical());
//    	autonomousMode.addObject("4) One Container", new ContainerToAutoZoneByOptical());
//    	autonomousMode.addObject("5) One Container One Tote", new ContainerSpinToteByOptical());
//    	autonomousMode.addObject("6) Container Tote Tote", new ContainerToteSpinToteByOptical());
		autonomousMode.addDefault("3) One Tote", new ToteToAutozone());
    	autonomousMode.addObject("4) One Container", new ContainerToAutoZone());
    	autonomousMode.addObject("5) One Container One Tote", new ContainerSpinTote());
    	autonomousMode.addObject("6) Container Tote Tote", new ContainerToteSpinTote());
    	autonomousMode.addObject("7) Grab Can No Move", new CanGrabNoMove());
    	autonomousMode.addObject("8) Grab Can Move Back", new CanGrabMoveBack());
    	autonomousMode.addObject("9) Grab Can Go To Next", new CanGrabMoveToNext());
//    	autonomousMode.addObject("Platform forward", new DriveOpticalAndEncoder(AutoConstants.CLAW_LENGTH, true));
//    	autonomousMode.addObject("Platform backward", new DriveOpticalAndEncoder(AutoConstants.ROBOT_LENGTH, false));
    	SmartDashboard.putData("Autonomous Selection", autonomousMode);
    }
    
    /**
     * Sends every subsystem to the Smart Dashboard
     */
    private void sendDashboardSubsystems() {
    	SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(clawElevator);
		SmartDashboard.putData(claw);
		SmartDashboard.putData(indexer);
		SmartDashboard.putData(gucciGrabber);
//		SmartDashboard.putData(visionProcessor);
    }
}
