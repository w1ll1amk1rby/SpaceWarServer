/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import java.util.Collections;
import java.util.List;
import world.observers.Observer;
import world.ships.Ship;
import world.ships.parts.guns.Projectile;

/**
 * an understanding of the world without being able to edit it.
 * @author William Kirby
 */
public class WorldContext {
    
    private final List<Ship> ships;
    private final List<Observer> observers;
    private final List<Projectile> projectiles;
    private final List<Projectile> unModProjectiles;
    
    /**
     * basic constructor
     * @param ships: the ships of the world
     * @param observers: the observers of the world
     * @param projectiles: projectiles of the world
     */
    public WorldContext(List<Ship> ships,List<Observer> observers,List<Projectile> projectiles) {
        this.ships = Collections.unmodifiableList(ships);
        this.observers = Collections.unmodifiableList(observers);
        this.projectiles = projectiles;
        this.unModProjectiles = Collections.unmodifiableList(projectiles);
    }
    
    /**
     * gets a list of ships present in the world.
     * @return an unmodifiable list of ships in the world.
     */
    public List<Ship> getUnmodShips() {
        return this.ships;
    }
    
    /**
     * gets a list of ships present in the world
     * @return an unmodifiable list of observers in the world.
     */
    public List<Observer> getUnmodObservers() {
        return this.observers;
    }
    
    /**
     * add a projectile to the world context.
     * @param projectile: projectile to add
     */
    public void addProjectileToWorld(Projectile projectile) {
        this.projectiles.add(projectile);
        for(int x = 0;x < this.observers.size();x++) {
            this.observers.get(x).addProjectileToView(projectile);
        }
    }
    
    /**
     * get a list of projectiles in the world (unmodable list)
     * @return list of projectiles in the world
     */
    public List<Projectile> getUnmodProjectiles() {
        return this.unModProjectiles;
    }
}
