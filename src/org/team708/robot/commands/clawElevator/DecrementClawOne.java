package org.team708.robot.commands.clawElevator;

import org.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DecrementClawOne extends CommandGroup {
    
    public  DecrementClawOne() {
    	requires(Robot.clawElevator);
    	
    	addSequential(new MoveDownOffSwitch());
    	addSequential(new MoveDownOneSwitch());
    }
    
    @Override
    public void interrupted() {
    	Robot.clawElevator.stop();
    }
}
