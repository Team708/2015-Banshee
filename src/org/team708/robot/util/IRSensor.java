/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.team708.robot.util;

/**
 *
 * @author 708
 */
public class IRSensor extends DistanceSensor{

    //infrareds
    public static final Model GP2Y0A02YK0F = new Model(0.4,2.55,0.00667,.05);
    public static final Model GP2Y0A21YK0F = new Model(0.4,3.0,.0125,0.143);

    public IRSensor(int channel,Model m){
        super(channel,m);
    }
    
     public double getDistance(){
         return 1/(Math708.lerp(model.getLowV(), model.getLowD(), model.getHighV(), model.getHighD(), getVoltage()) * 2.54);
     }
     
     public double getAverageDistance() {
         return 1/(Math708.lerp(model.getLowV(), model.getLowD(), model.getHighV(), model.getHighD(), getAverageVoltage()) * 2.54);
     }
}
