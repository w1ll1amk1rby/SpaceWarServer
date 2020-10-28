/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.ships;

import world.ships.parts.Hull;
import world.ships.parts.Thruster;
import world.ships.parts.ShipLogic;
import world.ships.parts.Radar;
import java.util.List;
import world.WorldContext;
import world.coords.HistoryCoord;
import world.hitBoxes.CircleHitBox;
import world.entitys.PhysicalObject;
import world.ships.parts.guns.Gun;
import world.ships.parts.guns.Projectile;

/**
 * a basic abstract ship class
 * @author William Kirby
 */
public abstract class Ship extends PhysicalObject {
    
    private static int shipIDCounter = 0;
    
    protected final int uniqueID;
    protected final Radar radar;
    protected final Hull hull;
    protected final ShipLogic shipLogic;
    protected final Thruster thruster;
    protected final Gun gun;
    protected boolean shouldRespawn;
    
    /**
     * basic constructor
     * @param centre: centre of the ship.
     * @param mass: mass of the ship.
     * @param hitbox: hit box of the ship.
     * @param radar: the radar of the ship.
     * @param hull: the hull of the ship.
     * @param shipLogic: the logic of the ship.
     * @param thruster: the thruster of the ship.
     * @param gun: gun of the ship
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public Ship(HistoryCoord centre, double mass, 
            CircleHitBox hitbox, 
            Radar radar, 
            Hull hull,
            ShipLogic shipLogic,
            Thruster thruster,
            Gun gun) {
        super(centre, mass, hitbox);
        this.radar = radar;
        this.hull = hull;
        this.shouldRespawn = true;
        this.shipLogic = shipLogic;
        this.thruster = thruster;
        this.thruster.setObjectToPush(this); // THIS SHOULD BE FINE
        this.gun = gun;
        this.gun.setOwner(this); // THIS SHOULD BE FINE
        this.uniqueID = Ship.getAUniqueID();
    }
    
    /**
     *  get the radar of the ship
     * @return the radar of the ship
     */
    public Radar getRadar() {
        return this.radar;
    }
    
    /**
     * gets the hull of the ship which tracks its damage
     * @return get the hull of the ship
     */
    public Hull getHull() {
        return this.hull;
    }
    
    /**
     * get the thruster of the ship
     * @return the thruster of the ship
     */
    public Thruster getThruster() {
        return this.thruster;
    }
    
    /**
     * get the gun of the ship
     * @return the gun of the ship
     */
    public Gun getGun() {
        return this.gun;
    }
    
    /**
     * get if the ship should respawn after death
     * @return if the ship should respawn
     */
    public boolean shouldRespawn() {
        return this.shouldRespawn;
    }
    
    /**
     * inform the ship of its death
     */
    public void informShipOfDeath() {
        this.shipLogic.informOfDeath();
    }
    
    /**
     * reset the data of the ship
     */
    public void resetStats() {
        this.hull.setHealth(this.hull.getMaxHealth());
        this.velocityX = 0;
        this.velocityY = 0;
        this.gun.resetAmmo();
    }
    
    /**
     * get the ship to calculate its next move.
     */
    public void prepareNextMove() {
        List<Ship> ships = this.radar.getVisibleShips();
        ships.remove(this);
        List<Projectile> projectiles = this.radar.getVisibleProjectiles();
        this.shipLogic.calculateNextMove(this,ships,projectiles);
    }
    
    /**
     * get the ship to make its next move
     */
    public void makeNextMove() {
        // thrust the ship in the direction that was requested.
        this.thruster.Thrust(this.shipLogic.getThrustAmount(),this.shipLogic.getThrustDirection());
        this.gun.attemptToFire(this.shipLogic.getTargetCoord());
        this.gun.reload();
    }
    
    /**
     * update the worldContext for the given ship
     * @param wc: the world context
     */
    public void updateWorldContext(WorldContext wc) {
        this.radar.updateWorldContext(wc);
        this.gun.updateWorldContext(wc);
    }
    
    /**
     * get a unique ship id for identification purposes
     * @return the unique id
     */
    private synchronized static int getAUniqueID() {
        int id = Ship.shipIDCounter;
        Ship.shipIDCounter++;
        return id;
    }
    
    /**
     * get unique ship id
     * @return the unique id of the ship
     */
    public int getID() {
        return this.uniqueID;
    }
    
    /**
     * set if the ship should respawn. 
     * default spawn is true;
     * @param respawn: if the ship should respawn
     */
    public void setRespawn(boolean respawn) {
        this.shouldRespawn = respawn;
    }
}