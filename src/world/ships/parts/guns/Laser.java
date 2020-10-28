/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.ships.parts.guns;
import world.coords.ICoord;
import world.ships.Ship;

/**
 * basic class for a laser which is used to inform observers of visible lasers
 * @author William Kirby
 */
public class Laser {
    
    private static int laserIDCounter;
    
    private final int id;
    private final Ship owner;
    private final ICoord target;
    
    /**
     * basic constructor for the laser
     * @param owner: who shot the laser
     * @param target: where the laser is aiming
     */
    public Laser(Ship owner,ICoord target) {
        this.id = getAUniqueID();
        this.owner = owner;
        this.target = target;
    }
    
    /**
     * get a unique ship id for identification purposes
     * @return the unique id
     */
    private synchronized static int getAUniqueID() {
        int id = Laser.laserIDCounter;
        Laser.laserIDCounter++;
        return id;
    }
    
    /**
     * get the unique id of the laster
     * @return the unique id of the laser
     */
    public int getID() {
        return this.id;
    }
    
    /**
     * return the target coordinate
     * @return the coord of where the laser is targeted
     */
    public ICoord getTarget() {
        return this.target;
    }
    
    /**
     * return the owner of the laser
     * @return the ship that fired the laser
     */
    public Ship getOwner() {
        return this.owner;
    }
}
