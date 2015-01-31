/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.team708.robot.util;

/**
 * This class represents a sensor that can read in
 * the robot's relative rotation from a starting position.
 * It is meant to be a wrapper for Encoders and Gryos.
 *
 * Compass:
 *
 *          | 0 deg
 *    270_     _ 90
 *
 *          | 180
 * @author Connor Willison
 */
public abstract class RotationSensor {

    /**
     * Returns the relative rotation since the last
     * reset.
     * @return
     */
    public abstract double getAngle();

    /**
     * Resets the rotation value to 0.0.
     */
    public abstract void reset();
}
