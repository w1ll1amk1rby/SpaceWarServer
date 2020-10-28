/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.entitys;
import world.coords.Coord;
import world.coords.HistoryCoord;
import world.coords.ICoord;
import world.hitBoxes.CircleHitBox;

/**
 * physical object that has mass and have force applied to it
 * @author William Kirby
 */
public class PhysicalObject extends MovingEntity {
    
    
    protected double mass; // mass of the given object
    protected final CircleHitBox hitbox; // hit box of the physical object
     
    /**
     * basic constructor 
     * @param centre: centre of the physical object
     * @param mass: mass of the object
     * @param hitbox: hit box of the object
     */
    public PhysicalObject(HistoryCoord centre,double mass,CircleHitBox hitbox) {
        super(centre);
        this.mass = mass;
        this.hitbox = hitbox;
    }
    
    /**
     * get the hitBox of the physical object
     * @return the hitBox of the object
     */
    public CircleHitBox getHitBox() {
        return this.hitbox;
    }
    
    /**
     * get the mass of the object
     * @return the mass of the object.
     */
    public double getMass() {
        return this.mass;
    }
    
    /**
     * set the mass of the object
     * @param mass: the mass to set the object as.
     */
    public void setMass(double mass) {
        this.mass = mass;
    }
    
    /**
     * apply momentum on object
     * @param momentumX: momentum to apply in the x axis
     * @param momentumY: momentum to apply in the y axis
     */
    public void applyMomentum(double momentumX,double momentumY) {
        this.applyMomentumX(momentumX);
        this.applyMomentumY(momentumY);
    }
   
    /**
     * apply momentum on object in x axis
     * @param momentumX: momentum to apply in the x axis
     */
    public void applyMomentumX(double momentumX) {
        this.applyVelocityX(momentumX/this.mass);
    }
    
    /**
     * apply momentum on object in y axis
     * @param momentumY: momentum to apply in the y axis
     */
    public void applyMomentumY(double momentumY) {
        this.applyVelocityY(momentumY/this.mass);
    }
    
    /**
     * test if the objects collided last movement
     * @param object: object to test for collision
     * @return if these two objects collided last tick
     */
    public boolean didObjectsCollideLastMove(PhysicalObject object) {
    // get the last position
    ICoord previousPos = this.historyCoord.getLastPosition();
    ICoord otherPrePos = object.historyCoord.getLastPosition();
    // get the time the entities were last closest (on last tick)    
    double time = this.getTimeOfClosestApproachFromLast(object);
    
    // get coord where this was at time
    Coord thisAtTime = new Coord(previousPos.getX() + time*this.lastVelocityX,
            previousPos.getY() + time*this.lastVelocityY);
    // get coord where object was at time
    Coord objectAtTime = new Coord(otherPrePos.getX() + time*object.lastVelocityX,
            otherPrePos.getY() + time*object.lastVelocityY);
    // recreate hitboxes at the given moment
    CircleHitBox thisHitBox = this.hitbox.recreate(thisAtTime);
    CircleHitBox objectBox = object.hitbox.recreate(objectAtTime);
    // if they were touching at this time then return true
    return thisHitBox.isTouching(objectBox);
    }

}