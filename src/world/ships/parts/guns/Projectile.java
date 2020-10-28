/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.ships.parts.guns;

import java.util.List;
import world.WorldContext;
import world.coords.HistoryCoord;
import world.entitys.PhysicalObject;
import world.hitBoxes.CircleHitBox;
import world.ships.Ship;

/**
 * basic projectile
 * @author William Kirby
 */
public class Projectile extends PhysicalObject {
    
    private final static double RADIUS = 0.1;
    private final static double MASS = 0.5;
    private final static int DAMAGE = 50;
    private static int idCounter = 0;
    private final WorldContext wc;
    private final Ship owner;
    private boolean destroyed;
    private final int id;
    
    /**
     * basic constructor for a projectile
     * @param coord: where the projectile exists in the world
     * @param owner: the owner of the projectile
     * @param wc: the world the projectile exists in
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public Projectile(HistoryCoord coord, Ship owner, WorldContext wc) {
        super(coord,MASS,new CircleHitBox(coord,RADIUS));
        this.id = Projectile.generateAUniqueID();
        this.wc = wc;
        this.owner = owner;
        this.destroyed = false;
    }
    
    /**
     * destroy the projectile
     */
    public void destroy() {
        this.destroyed = true;
    }
    
    /**
     * return if this projectile has been destroyed
     * @return true if the projectile has been destroyed
     */
    public boolean isDestroyed() {
        return this.destroyed;
    }
    
    /**
     * test if the projectile has collided with any ships
     */
    public void checkForCollisions() {
        List<Ship> ships = this.wc.getUnmodShips();
        for(int x = 0;x < ships.size();x++) {
            Ship ship = ships.get(x);
            if(ship == owner) {
                break;
            }
            else if(this.didObjectsCollideLastMove(ship)) {
                ship.getHull().setHealth(ship.getHull().getHealth() - DAMAGE);
            }
        }
    }

    /**
     * get the unique id of the ship
     * @return the unique id
     */
    public int getID() {
        return this.id;
    }
    
    /**
     * generate a unique id for a new projectile
     * @return a new id
     */
    private synchronized static int generateAUniqueID() {
        int id = Projectile.idCounter;
        Projectile.idCounter = Projectile.idCounter + 1;
        return id;
    }
    
}
