/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.team708.robot.util;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * This class represents a rangefinding sensor that determines
 * the distance of an object placed in front of it.
 * @author 708
 */
public abstract class DistanceSensor{
    
    private AnalogInput sensor;   //channel to read voltages from
    protected Model model;          //model is a structure used to easily pass in parameters
                                    //from the sensor's datasheet.

    /*
     * Boundaries for triggering. The sensor is triggered (returns
     * boolean true) when the distance is between the low trigger bound
     * and the high trigger bound. If invertTriggerRange is true,
     * the sensor is triggered when the distance is outside of the range.
     */
    private double lowTriggerBound = -1;
    private double highTriggerBound = -1;
    private boolean invertTriggerRange = false;

    public DistanceSensor(int channel,Model m) {
        sensor = new AnalogInput(channel);
        model = m;
    }
    
    public DistanceSensor(int channel,Model m,double lowTrigger,double highTrigger,boolean invertTriggerRange){
        this(channel,m);
        
        this.lowTriggerBound = lowTrigger;
        this.highTriggerBound = highTrigger;
        this.invertTriggerRange = invertTriggerRange;
    }

    /**
     * Processes the voltage according to the sensor's specifications
     * to yield a distance.
     * @return
     */
    public abstract double getDistance();
    
    public abstract double getAverageDistance();
    
    /**
     * Returns the distance, corrected for weird voltage values
     * @return
     */
    public double getClippedDistance() {
    	double distance = getDistance();
    	
    	if (distance < 0.0) {
    		return -distance;
    	} else {
    		return distance;
    	}
    }
    
    public double getClippedAverageDistance() {
    	double distance = getAverageDistance();
    	
    	if (distance < 0.0) {
    		return -distance;
    	} else {
    		return distance;
    	}
    }

    /**
     * Returns the raw voltage read from this sensor.
     * @return 
     */
    public double getVoltage(){
        return sensor.getVoltage();
    }
    
    /**
     * Returns the average voltage read from this sensor.
     * @return
     */
    public double getAverageVoltage() {
        return sensor.getAverageVoltage();
    }

    /**
     * Check whether the sensor is triggered based on triggering boundaries
     * specified in the constructor or modified via setTriggerBounds().
     * @return 
     */
    public boolean isTriggered(){
        //return false if the programmer has not specified boundaries
        if(lowTriggerBound < 0 || highTriggerBound < 0) {
            return false;
        }
        
        //calculate distance value
        double dist = getDistance();
        
        //check if the sensor is triggered
        if(invertTriggerRange)
        {
            return dist <= lowTriggerBound || dist >= highTriggerBound;
        }else{
            return dist >= lowTriggerBound && dist <= highTriggerBound;
        }
    }

    /**
     * Modify the trigger boundaries for the sensor.
     * This call can be used before an call to isTriggered() to
     * specify how the distance value is to be processed.
     * @param lowTriggerBound - asks: is x >= low?
     * @param highTriggerBound - asks: is x also <= high?
     * @param invertedTriggerRange - switches direction of comparison operators
     */
    public void setTriggerBounds(double lowTriggerBound, double highTriggerBound, boolean invertedTriggerRange)
    {
        this.highTriggerBound = highTriggerBound;
        this.lowTriggerBound = lowTriggerBound;
        this.invertTriggerRange = invertedTriggerRange;
    }
    
    public double getLowTriggerBound() {
        return lowTriggerBound;
    }
    
    public double getHighTriggerBound() {
        return highTriggerBound;
    }
    
    protected static class Model{
        private double lowV,highV,lowD,highD;
        private double vrange,drange,scale;

        protected Model(double lowV, double highV, double lowD, double highD) {
            this.lowV = lowV;
            this.highV = highV;
            this.lowD = lowD;
            this.highD = highD;

            vrange = highV - lowV;
            drange = highD - lowD;

            scale = drange/vrange;
        }

        protected Model(double voltsPerInch){
            this(0,0,0,0);  //not known
            scale = 1/voltsPerInch;
        }

        public double getDrange() {
            return drange;
        }

        public double getHighD() {
            return highD;
        }

        public double getHighV() {
            return highV;
        }

        public double getLowD() {
            return lowD;
        }

        public double getLowV() {
            return lowV;
        }

        public double getVrange() {
            return vrange;
        }

        public double getScale(){
            return scale;
        }
     }
}
