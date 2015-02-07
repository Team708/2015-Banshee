
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
import org.team708.robot.commands.autonomous.DriveInSquare;
import org.team708.robot.commands.autonomous.OneContainerOneTote;
import org.team708.robot.commands.autonomous.ThreeTotes;
import org.team708.robot.commands.visionProcessor.FollowYellowTote;
import org.team708.robot.subsystems.Drivetrain;
import org.team708.robot.subsystems.VisionProcessor;
import org.team708.robot.subsystems.Claw;
import org.team708.robot.subsystems.ClawElevator;
import org.team708.robot.subsystems.HockeyStick;
import org.team708.robot.subsystems.Intake;
import org.team708.robot.subsystems.ToteElevator;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    
	public static Drivetrain drivetrain;
	public static VisionProcessor visionProcessor;
	public static OI oi;

	//SmartDashboard
    Preferences robotPreferences;
    
    //creates timer for the SmartDashboard stat sending
    Timer statsTimer;                               // Timer used for Smart Dash statistics
    private final double sendStatsIntervalSec = .5;		// Interval between statistic reporting
    
    public static Intake intake;
	public static HockeyStick hockeyStick;
	public static ToteElevator toteElevator;
	public static Claw claw;
	public static ClawElevator clawElevator;

    Command autonomousCommand;
    SendableChooser autonomousMode;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	//initialize timer periodic debug messages
        statsTimer = new Timer();
        statsTimer.start();
        
        drivetrain = new Drivetrain();
		oi = new OI();
		visionProcessor = new VisionProcessor();
		intake = new Intake();
		hockeyStick = new HockeyStick();
		toteElevator = new ToteElevator();
		claw = new Claw();
		clawElevator = new ClawElevator();
		
		SmartDashboard.putData(drivetrain);
		LiveWindow.addActuator("PID", "PID", drivetrain.getPIDController());
		
        // instantiate the command used for the autonomous period
		autonomousMode = new SendableChooser();
		queueAutonomousModes();
		setPIDPreferences();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		sendStatistics();
	}

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
    
    private void sendStatistics() {
        if (statsTimer.get() >= sendStatsIntervalSec) {
            statsTimer.reset();

            // Various debug information
            drivetrain.sendToDashboard();
            visionProcessor.sendToDashboard();
            intake.sendToDashboard();
        }
    }
    
    private void queueAutonomousModes() {
    	autonomousMode.addDefault("Do Nothing", new DoNothing());
    	autonomousMode.addObject("Drive in Square", new DriveInSquare());
    	autonomousMode.addObject("Follow Tote", new FollowYellowTote());
    	autonomousMode.addObject("One Container", new OneContainerOneTote());
    	autonomousMode.addObject("Three Totes", new ThreeTotes());
    	SmartDashboard.putData("Autonomous Selection", autonomousMode);
    }
    
    private void setPIDPreferences() {
    	drivetrain.setPIDGain('p', robotPreferences.getDouble("P", drivetrain.getPIDGain('p')));
    	drivetrain.setPIDGain('i', robotPreferences.getDouble("I", drivetrain.getPIDGain('i')));
    	drivetrain.setPIDGain('d', robotPreferences.getDouble("D", drivetrain.getPIDGain('d')));
    }
}
