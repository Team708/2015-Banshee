/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team708.robot.util;

import edu.wpi.first.wpilibj.Timer;

/**
 * Miscellaneous math functions.
 * @author Connor Willison & Nam Tran
 */
public class Math708 {
    
    /**
     * Performs linear interpolation between the points (x1,y1) and (x2,y2)
     * and returns the interpolated value of the function at x_result.
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return 
     */
    public static double lerp(double x1,double y1, double x2, double y2,double x_result)
    {
        return ((x_result - x1) * (y2 - y1) / (x2 - x1)) + y1;
    }
    
    /**
     * Used to round a floating point value to
     * a given decimal place.
     * @param n The floating point value.
     * @param dig The decimal place to round to (use negative
     * numbers for places > 0.
     * @return 
     */
    public static double round(double n,int dig)
    {
        double tens = Math.pow(10,dig);
        return ((int)((n * tens) + .5)) / tens;
    }
    
    /**
     * Returns the length of a vector with the specified
     * components.
     * @param x
     * @param y
     * @return 
     */
    public static double length(double x,double y)
    {
        return Math.sqrt(lengthSquared(x,y));
    }
    
    /**
     * Returns c^2 from the Pythagorean Theorem (a^2 + b^2 = c^2).
    * @param x
     * @param y
     * @return 
     */
    public static double lengthSquared(double x,double y)
    {
        return x * x + y * y;
    }
    
    /**
     * Returns true if x and y are both negative or both positive.
     * Returns false if one value is negative and the other is positive.
     * @param x
     * @param y
     * @return 
     */
    public static boolean AreSameSign(double x, double y)
    {
        return (x * y) >= 0;
    }
    
    /**
     * Checks to see if a value is between a high and lo parameter.  If it is
     * higher than the upper bound it returns the upper bound, if it is lower
     * than the lower bound it returns the lower bound, otherwise it returns x.
     * @param x
     * @param low
     * @param high
     * @return 
     */
    public static double makeWithin(double x, double low, double high)
    {
        return Math.min(high,Math.max(low, x));
    }
    
    /**
     * Generates a square wave with the given on/off time.
     * @param timer
     * @param onTimeSec
     * @return 
     */
    public static boolean squareWave(Timer timer,double onTimeSec,boolean prevValue)
    {
        if(timer.get() >= onTimeSec)
        {
            timer.reset();
            return !prevValue;
        }
        return prevValue;
    }
    
    /**
     * Gets the percent error of a sensor reading with a desired value
     * @param currentValue
     * @param goalValue
     * @return
     */
    public static double getPercentError(double currentValue, double goalValue)
    {
    	return (currentValue - goalValue) / goalValue;
    }
    
    /**
     * Gets the percent error of a sensor reading with a desired value,
     * but clips it to prevent the error being too low to overcome torque
     * or so high that the error proposes mechanical issues
     * @param currentValue
     * @param goalValue
     * @param minimumValue
     * @param maximumValue
     * @return
     */
    public static double getClippedPercentError(double currentValue, double goalValue, double minimumValue, double maximumValue) {
    	return makeWithin(getPercentError(currentValue, goalValue), minimumValue, maximumValue);
    }
    
    /**
     * Checks if a sensor reading is within a threshold of a desired value
     * @param currentValue
     * @param goalValue
     * @param threshold
     * @return
     */
    public static boolean isWithinThreshold(double currentValue, double goalValue, double threshold) {
    	return Math.abs(goalValue - currentValue) <= threshold;
    }
}
