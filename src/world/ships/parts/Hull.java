/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.ships.parts;

/**
 *
 * @author William Kirby
 */
public class Hull {
    
    private final int maxHealth;
    private int health;
    
    public Hull(int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
    }
    
    /**
     * get current health of the ship.
     * @return current health level
     */
    public int getHealth() {
        return this.health;
    }
    
    /**
     * set the current health of the ship.
     * Health cant be larger than max health.
     * @param health: health to set the ship as. 
     */
    public void setHealth(int health) {
        int maxH = this.maxHealth;
        if(maxH < health) {
            this.health = maxH;
        }
        else {
            this.health = health;
        }
    }
    
    /**
     * get the max health of the ship.
     * @return the ships max health
     */
    public int getMaxHealth() {
        return this.maxHealth;
    }
    
    /**
     * see if the ships health has reached critical
     * @return true if the health is below 0
     */
    public boolean isHealthCritical() {
        return this.health <= 0;
    }
}
