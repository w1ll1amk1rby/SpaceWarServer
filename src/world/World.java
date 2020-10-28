/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import java.util.List;
import java.util.ArrayList;
import world.coords.ICoord;
import world.observers.Observer;
import world.ships.Ship;
import world.ships.parts.guns.Projectile;

/**
 * world is where everything exists and where the world comes to interact with each other
 * @author William Kirby
 */
public class World {
    
    public static final double MAX_HEIGHT = 1080;
    public static final double MIN_HEIGHT = 0;
    
    public static final double MAX_WIDTH = 1920;
    public static final double MIN_WIDTH = 0;
    
    private final List<Ship> ships; // list of ships that exist in the world
    private final List<Observer> observers; // list of observers viewing the world
    private final WorldContext worldContext; // an unmodifiable understanding of the world
    private final List<Projectile> projectiles; // the projectiles currently in the world
    
    /**
     * basic world constructor
     */
    public World() {
        this.ships = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.worldContext = new WorldContext(this.ships,this.observers,this.projectiles);
    }
    
    /**
     * process a tick of the world
     */
    public synchronized void processWorldTick() {
        
        // update all ships info and request their next actions
        for(int x = 0;x < this.ships.size();x++) {
            this.ships.get(x).prepareNextMove();
        }
        // allow for thinking threads time to end
        // and avoid overloading observers
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            // do nothing
        }
        
        // now make all the ships actions
        for(int x = 0;x < this.ships.size();x++) {
            this.ships.get(x).makeNextMove();
        }
        
        this.handleDeadProjectiles(); // if any ships killed projectiles
        
        // now make all the ships move
        for(int x = 0;x < this.ships.size();x++)  {
            Ship ship = this.ships.get(x);
            ship.enactMotion();
            for(int i = 0;i < this.observers.size();i++) {
                this.observers.get(i).updateShip(ship);
            }
        }
        
        // make all the projectiles move
        for(int x = 0;x < this.projectiles.size();x++) {
            Projectile projectile = this.projectiles.get(x);
            projectile.enactMotion();
            for(int i = 0;i < this.observers.size();i++) {
                this.observers.get(i).updateProjectile(projectile);
            }
        }
        
        // allow the projectiles to collide
        for(int x = 0;x < this.projectiles.size();x++) {
            this.projectiles.get(x).checkForCollisions();
        }
        
        // check if any projectiles are out of bounds
        for(int x = 0;x < this.projectiles.size();x++) {
            Projectile projectile = this.projectiles.get(x);
            if(this.outOfBounds(projectile.getCentre())) { // kill the ship
                projectile.destroy();
            }
        }
        
        // check if any ships are out of bounds
        for(int x = 0;x < this.ships.size();x++) {
            Ship ship = this.ships.get(x);
            if(this.outOfBounds(ship.getCentre())) { // kill the ship
                ship.getHull().setHealth(-100);
            }
        }
        
        // if any of the ships or projectiles died then handle deaths
        this.handleDeadShips();
        this.handleDeadProjectiles();
    }
    
    /**
     * if a ship has died then reset the ship values or remove the ship
     */
    private void handleDeadShips() {
        // remove dead ships
        for(int i = 0;i < this.ships.size();i++) {
            Ship deathTest = this.ships.get(i);
            if(deathTest.getHull().isHealthCritical()) { // if dead
                deathTest.informShipOfDeath();
                if(deathTest.shouldRespawn()) {
                    this.respawnShip(deathTest);
                }
                else { // if shouldn't respawn then remove
                    this.removeShipFromWorld(deathTest);
                    i--;
                }
            }
        }
    }
    
    private void handleDeadProjectiles() {
        // if any projectiles were destroyed then remove them
        for(int x = 0;x < this.projectiles.size();x++) {
            Projectile projectile = this.projectiles.get(x);
            if(projectile.isDestroyed()) {
                this.projectiles.remove(projectile);
                for(int i = 0;i < this.observers.size();i++) {
                    this.observers.get(i).removeProjectile(projectile);
                }
                x--;
            }
        }
    }
    
    /**
     * add a ship to the world
     * @param ship: ship to add
     */
    public synchronized void addShipToWorld(Ship ship,boolean changeCoords) {
        this.ships.add(ship); // add ship to the world list
        ship.updateWorldContext(this.worldContext);
        if(changeCoords == true) {
            this.setSpawnPoint(ship);
        }
        // add ship to the observers view
        for(int x = 0;x < this.observers.size();x++) {
            this.observers.get(x).addShipToView(ship);
        }
    }
    
    /**
     * remove the given ship from the world
     * @param ship: ship to remove
     */
    private synchronized void removeShipFromWorld(Ship ship) {
        this.ships.remove(ship);
        // remove ship from observer
        for(int x = 0;x < this.observers.size();x++) {
            this.observers.get(x).removeShipFromView(ship);
        }
        ship.updateWorldContext(null); // remove connection from the world
    }
    
    /**
     * add a new observer to the world.
     * @param observer: observer to add to the world.
     */
    public synchronized void addObserverToWorld(Observer observer) {
        for(int x = 0;x < this.ships.size();x++) { // send all ships
            observer.addShipToView(this.ships.get(x));
        }
        for(int x = 0;x < this.projectiles.size();x++) { // send all projectiles
            observer.addProjectileToView(this.projectiles.get(x));
        }        
        this.observers.add(observer);
    }
    
    /**
     * remove an observer from the world.
     * @param observer: observer to remove from the world.
     */
    public synchronized void removeObserverFromWorld(Observer observer) {
        this.observers.remove(observer);
    }
    
    /**
     * reset the stats and give a respawn location.
     * @param ship: ship to respawn.
     */
    private void respawnShip(Ship ship) {
        ship.resetStats();
        this.setSpawnPoint(ship);
        // update all observers of the ship
        for(int x = 0;x < this.observers.size();x++) {
            this.observers.get(x).updateShip(ship);
        }
    }
    
    /**
     * set the spawn point of the ship
     * @param entity: entity to set spawn point of.
     */
    private void setSpawnPoint(Ship ship) {
        double x = ((World.MAX_WIDTH - World.MIN_WIDTH)* Math.random()) + World.MIN_WIDTH;
        double y = ((World.MAX_HEIGHT - World.MIN_HEIGHT)* Math.random()) + World.MIN_HEIGHT;
        ship.getCentre().resetBothCoords(x, y);
    }
    
    /**
     * test if a given ship is out of bounds of the world
     * @param ship: ship to test
     * @return if the coord is out of bounds of the world
     */
    private boolean outOfBounds(ICoord coord) {
        double x = coord.getX();
        double y = coord.getY();
        // if is not in the bounds of the world
        return !((x >= World.MIN_WIDTH) && (x <= World.MAX_WIDTH) 
                && (y >= World.MIN_HEIGHT) && (y <= World.MAX_HEIGHT));
    }
}
