/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team708.robot.util;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 *
 * @author Nam Tran
 */
public class Potentiometer {
    
    private AnalogInput potentiometer;
    
    // Stuff based on potentiometer for finding the scaling factor in getAngle()
    private final int MIN_POTENTIOMETER_VOLTAGE = 0;
    private final int MAX_POTENTIOMETER_VOLTAGE = 5;
    private final int MIN_POTENTIOMETER_ANGLE = 0;
    private int maxPotentiometerAngle;
    
    public Potentiometer(int pwmPort, int numberOfRotations) {
        potentiometer = new AnalogInput(pwmPort);
        maxPotentiometerAngle = (numberOfRotations * 360);
    }
    
    public double getAngle() {
        // Finds the scaling factor for the voltage
        double scalingFactor = (maxPotentiometerAngle - MIN_POTENTIOMETER_ANGLE) / (MAX_POTENTIOMETER_VOLTAGE - MIN_POTENTIOMETER_VOLTAGE);
        
        double voltage = potentiometer.getVoltage();
        double offset = 0.0;
        
        return (scalingFactor * voltage) + offset;
    }
}
