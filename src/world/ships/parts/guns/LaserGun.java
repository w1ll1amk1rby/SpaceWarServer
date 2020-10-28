/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.ships.parts.guns;

import java.util.List;
import world.WorldContext;
import world.coords.CoordMath;
import world.coords.ICoord;
import world.observers.Observer;
import world.ships.Ship;

/**
 * a laser gun is a hit scan weapon 
 * @author William Kirby
 */
public class LaserGun extends Gun {
    
    private final double range;
    private final int damage;
    private Laser lastLaser;
    
    /**
     * basic constructor of the gun
     * @param damage: damage of the gun
     * @param range: range of the gun
     * @param maxAmmo: the max ammo the gun can store
     * @param reloadAmount: the amount of ammo the ship should gain per turn
     */
    public LaserGun(int damage,double range,int maxAmmo,double reloadAmount) {
        super(maxAmmo,reloadAmount);
        this.range = range;
        this.damage = damage;
        this.lastLaser = null;
    }
    
    /**
     * fire the gun at a given coordinate
     * @param targetCoord: the coord to target
     */
    @Override
    protected void fire(ICoord targetCoord) {
        // if is in range
        if(range > CoordMath.calculateDistance(owner.getCentre(),targetCoord)) {
            List<Ship> ships = this.wc.getUnmodShips();
            this.lastLaser = new Laser(owner,targetCoord);
            
            List<Observer> observers = this.wc.getUnmodObservers();
            
            for(int x = 0;x < observers.size();x++) { // inform observers of the firing
                observers.get(x).addLaserToView(this.lastLaser);
            }
            
            for(int x = 0;x < ships.size();x++) { // see if it hits a ship
                Ship ship = ships.get(x);
                // if has hit a given ship then do amount of damage
                if((ship != this.owner) && ship.getHitBox().containsCoord(targetCoord)) {
                    ship.getHull().setHealth(ship.getHull().getHealth() - damage);
                }
            }
        }
    }
    
    /**
     * remove the previously fired laser 
     * @param targetCoord: the target coord you want to fire at.
     */
    @Override
    public void attemptToFire(ICoord targetCoord) {
        // if the laser has been previously fired
        if(this.lastLaser != null) { // remove old laser from the view
            List<Observer> observers = this.wc.getUnmodObservers();
            for(int x = 0;x < observers.size();x++) {
                observers.get(x).removeLaserFromView(lastLaser);
            }
        }
        lastLaser = null;
        super.attemptToFire(targetCoord);
    
    }
    
    /**
     * update the understanding of the gun
     * @param wc: context of the world to set
     */
    @Override
    public void updateWorldContext(WorldContext wc) {
        if(lastLaser != null) {
            List<Observer> observers = this.wc.getUnmodObservers();
            for(int x = 0;x < observers.size();x++) { // inform observers of the firing
                observers.get(x).removeLaserFromView(this.lastLaser);
            }
        }
        this.lastLaser = null;
        super.updateWorldContext(wc);
    }
    
    /**
     * get the range of the laser gun
     * @return the range.
    */
    public double getRange() {
        return this.range;
    }
}
