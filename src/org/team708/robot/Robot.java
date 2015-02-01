
package org.team708.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.team708.robot.commands.DoNothing;
import org.team708.robot.subsystems.Claw;
import org.team708.robot.subsystems.ClawElevator;
import org.team708.robot.subsystems.Drivetrain;
import org.team708.robot.subsystems.HockeyStick;
import org.team708.robot.subsystems.Intake;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	//creates choose object on SmartDashboard
    Timer statsTimer;                               // Timer used for Smart Dash statistics
    private final double sendStatsIntervalSec = .5;		// Interval between statistic reporting
    
    public static final Intake intake = new Intake();
	public static final Drivetrain drivetrain = new Drivetrain();

	public static final HockeyStick hockeyStick = new HockeyStick();

	public static final Claw claw = new Claw();
	public static final ClawElevator clawElevator = new ClawElevator(); 

	public static OI oi;

    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	//initialize timer periodic debug messages
        statsTimer = new Timer();
        statsTimer.start();
         
		oi = new OI();
        // instantiate the command used for the autonomous period
        autonomousCommand = new DoNothing();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		sendStatistics();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
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
    public void disabledInit(){

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
            intake.sendToDashboard();
        }
    }
}
