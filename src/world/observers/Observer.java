/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.observers;

import world.ships.Ship;
import world.ships.parts.guns.Laser;
import world.ships.parts.guns.Projectile;

/**
 * observers view the world and get notified about changes.
 * @author William Kirby
 */
public abstract class Observer {
    
    /**
     * add a ship for the observer to view
     * @param ship: ship to add to the observer.
     */
    public abstract void addShipToView(Ship ship);
    
    /**
     * remove a given ship from the world view
     * @param ship: ship to remove from the observer
     */
    public abstract void removeShipFromView(Ship ship);
    
    /**
     * update the position of the given ship.
     * @param ship: ship to update
     */
    public abstract void updateShip(Ship ship);
    
    /**
     * add laser to the view
     * @param laser: laser to remove from the view
     */
    public abstract void removeLaserFromView(Laser laser);
    
    /**
     * remove laser from the view
     * @param laser: laser to add to the view
     */
    public abstract void addLaserToView(Laser laser); 
    
    /**
     * the projectile to add to the view
     * @param projectile: projectile to add to the world.
     */
    public abstract void addProjectileToView(Projectile projectile);
    
    /**
     * update the projectile on the view
     * @param projectile: projectile to update
     */
    public abstract void updateProjectile(Projectile projectile); 
    
    /**
     * remove projectile from the view
     * @param projectile: projectile to remove
     */
    public abstract void removeProjectile(Projectile projectile);
    
}
