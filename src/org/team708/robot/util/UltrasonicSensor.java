/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.team708.robot.util;

/**
 *
 * @author 708
 */
public class UltrasonicSensor extends DistanceSensor{

    //ultrasonics
    public static final Model MB1010 = new Model(.009766);
    public static final Model MB1340 = new Model(.012446);

    public UltrasonicSensor(int channel, Model m) {
        super(channel,m);
    }

    public double getRawDistance(){
        return (getVoltage() - model.getLowV()) * model.getScale() + model.getLowD();
    }

    public double getDistance() {
        return (getVoltage() - model.getLowV()) * model.getScale() + model.getLowD();
    }
    
    public double getAverageDistance() {
        return (getAverageVoltage() - model.getLowV()) * model.getScale() + model.getLowD();
    }
}
