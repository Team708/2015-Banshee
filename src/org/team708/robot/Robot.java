
package org.team708.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.team708.robot.commands.DoNothing;
import org.team708.robot.commands.autonomous.ClearStep;
import org.team708.robot.commands.autonomous.DriveInSquare;
import org.team708.robot.commands.autonomous.GetContainerGotoAutozone;
import org.team708.robot.commands.autonomous.HockeyStickClearToAutoZone;
import org.team708.robot.commands.autonomous.MoveToAutozone;
import org.team708.robot.commands.autonomous.OneContainerOneTote;
import org.team708.robot.commands.autonomous.OneToteToAuto;
import org.team708.robot.commands.autonomous.ThreeContainersToAuto;
import org.team708.robot.commands.autonomous.ThreeTotes;
import org.team708.robot.commands.autonomous.TwoToteOneContainer;
import org.team708.robot.commands.indexer.IndexerDown;
import org.team708.robot.commands.indexer.IndexerUp;
import org.team708.robot.commands.visionProcessor.FollowYellowTote;
import org.team708.robot.subsystems.Drivetrain;
import org.team708.robot.subsystems.VisionProcessor;
import org.team708.robot.subsystems.Claw;
import org.team708.robot.subsystems.ClawElevator;
import org.team708.robot.subsystems.HockeyStick;
import org.team708.robot.subsystems.Intake;
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
	public static VisionProcessor visionProcessor;
    public static Intake intake;
	public static HockeyStick hockeyStick;
	public static Indexer indexer;
	public static Claw claw;
	public static ClawElevator clawElevator;
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
		visionProcessor = new VisionProcessor();
		intake = new Intake();
		hockeyStick = new HockeyStick();
		indexer = new Indexer();
		claw = new Claw();
		clawElevator = new ClawElevator();
		
		oi = new OI();	// Initialises the OI. This MUST BE LAST or a NullPointerException will be thrown
		
		sendDashboardSubsystems();	// Sends each subsystem's currently running command to the Smart Dashboard
		
		autonomousMode = new SendableChooser();		// Initialises the Autonomous selection box
		queueAutonomousModes();						// Adds autonomous modes to the selection box
		setPIDPreferences();						// Adds PID gain constants to the Robot Preferences
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
            visionProcessor.sendToDashboard();
            intake.sendToDashboard();
            clawElevator.sendToDashboard();
            indexer.sendToSmartDashboard();
        }
    }
    
    /**
     * Adds every autonomous mode to the selection box and adds the box to the Smart Dashboard
     */
    private void queueAutonomousModes() {
    	autonomousMode.addDefault("Do Nothing", new DoNothing());
    	autonomousMode.addObject("Drive in Square", new DriveInSquare());
    	autonomousMode.addObject("Follow Tote", new FollowYellowTote());
    	autonomousMode.addObject("Clear Step", new ClearStep());
    	autonomousMode.addObject("One Container", new GetContainerGotoAutozone());
    	autonomousMode.addObject("Hockey Stick Shove All", new HockeyStickClearToAutoZone());
    	autonomousMode.addObject("Move to Autozone", new MoveToAutozone());
    	autonomousMode.addObject("One Container One Tote", new OneContainerOneTote());
    	autonomousMode.addObject("One Tote To Auto", new OneToteToAuto());
    	autonomousMode.addObject("Three Containers To Auto", new ThreeContainersToAuto());
    	autonomousMode.addObject("Three Totes", new ThreeTotes());
    	autonomousMode.addObject("Two Tote One Container Gucci Player", new TwoToteOneContainer());
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
		SmartDashboard.putData(visionProcessor);
		SmartDashboard.putData(hockeyStick);
		SmartDashboard.putData(intake);
		
		SmartDashboard.putData("Move Up", new IndexerUp());
		SmartDashboard.putData("Move Down", new IndexerDown());
    }
    
    /**
     * Adds PID gain constants to the Robot Preferences to allow for faster tuning
     */
    private void setPIDPreferences() {
    	// Attempts to get robot preferences to set for PID, but throws an exception if the SmartDashboard is not found
    	try {
    		drivetrain.setPIDGain('p', Preferences.getInstance().getDouble("P", drivetrain.getPIDGain('p')));
    		drivetrain.setPIDGain('i', Preferences.getInstance().getDouble("I", drivetrain.getPIDGain('i')));
    		drivetrain.setPIDGain('d', Preferences.getInstance().getDouble("D", drivetrain.getPIDGain('d')));
    	} catch (NullPointerException e) {
    		e.printStackTrace();
    	}
    	
    }
}
