/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.ships.parts;

import java.util.List;
import world.coords.Coord;
import world.coords.ICoord;
import world.ships.Ship;
import world.ships.parts.guns.Projectile;

/**
 * logic that the ship uses to work out its next move
 * @author William Kirby
 */
public abstract class ShipLogic {
    
    protected double thrustDirection = 0;
    protected double thrustAmount = 0;
    protected ICoord targetCoord = new Coord(0,0);
    
    /**
     * calculate the next move
     * @param ownedShip: ship that is owned 
     * @param ships: ships that are in view of the ship
     */
    public abstract void calculateNextMove(Ship ownedShip, List<Ship> ships,
            List<Projectile> projectiles);
    
    /**
     * inform the ship logic of death.
     */
    public abstract void informOfDeath();
    
    /**
     * get the intended thrust direction
     * @return the intended thrust direction
     */
    public double getThrustDirection() {
        return this.thrustDirection;
    }
    
    /**
     * get the intended thrust amount
     * @return the intended thrust amount
     */
    public double getThrustAmount() {
        return this.thrustAmount;
    }
    
    /**
     * get the coord the ship is targeting
     * @return the coord to target with the weapon
     */
    public ICoord getTargetCoord() {
        ICoord target = this.targetCoord;
        this.targetCoord = null;
        return target;
    }
}
