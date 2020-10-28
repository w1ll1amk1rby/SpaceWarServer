/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.ships.parts;

import world.entitys.PhysicalObject;

/**
 * basic thruster class used to propel ships
 * @author William Kirby
 */
public class Thruster {
    
    protected final double maxThrust;
    protected PhysicalObject parent;
    
    /**
     * basic constructor for the thruster
     * @param maxThrust: the maximum the thrust the engine is capable (force)
     */
    public Thruster(double maxThrust) {
        this.maxThrust = maxThrust;
    }
    
    /**
     * get the maximum the thruster is capable of.
     * @return the maximum the thruster is capable of.
     */
    public double getMaxThrust() {
        return this.maxThrust;
    }
    
    /**
     * set what the thruster is pushing.
     * @param object: object its pushing
     */
    public final void setObjectToPush(PhysicalObject object) {
        this.parent = object;
    }
    
    /**
     * apply thrust for 1 second to the object
     * @param thrust: the amount of thrust
     * @param angle: angle of the thrust
     */
    public void Thrust(double thrust,double angle) {
        if(thrust <= 0 ) {
            return;
        }
        else if(thrust > this.maxThrust) {
            thrust = this.maxThrust;
        }
        // work out the thrust in each axis
        double thrustX = thrust*Math.sin(Math.toRadians(angle));
        double thrustY = thrust*Math.cos(Math.toRadians(angle));
        this.parent.applyMomentum(thrustX, thrustY);
    }
}
