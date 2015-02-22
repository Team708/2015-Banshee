package org.team708.robot.commands.clawElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DecrementClawOne extends CommandGroup {
    
    public  DecrementClawOne() {
    	
    	addSequential(new MoveDownOffSwitch());
    	addSequential(new MoveDownOneSwitch());
    	
    }
}
