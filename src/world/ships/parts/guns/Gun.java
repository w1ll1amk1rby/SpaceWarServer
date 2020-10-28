/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.ships.parts.guns;

import world.WorldContext;
import world.coords.ICoord;
import world.ships.Ship;

/**
 * a basic gun for a ship
 * @author William Kirby
 */
public abstract class Gun {
    
    protected WorldContext wc;
    protected Ship owner;
    private double ammoCount;
    private final double reloadAmount;
    private final int maxAmmo;
    
    /**
     * 
     * @param maxAmmo
     * @param reloadAmount 
     */
    public Gun(int maxAmmo,double reloadAmount) {
        this.ammoCount = maxAmmo;
        this.maxAmmo = maxAmmo;
        this.reloadAmount = reloadAmount;
    }
    
    /**
     * fire the gun at a given coordinate
     * @param targetCoord: the coord to target
     */
    protected abstract void fire(ICoord targetCoord);
    
    /**
     * attempt to fire the gun if the gun has ammo
     * @param targetCoord: target you are attempting to fire at.
     */
    public void attemptToFire(ICoord targetCoord) {
        if((targetCoord != null) && (ammoCount >= 1)) {
            this.fire(targetCoord);
            this.ammoCount = this.ammoCount - 1;
        }
    }
    
    /**
     * reload the gun by its specified amount
     */
    public void reload() {
        this.ammoCount = this.ammoCount + this.reloadAmount;
        if(this.ammoCount > this.maxAmmo) {
            this.ammoCount = this.maxAmmo;
        }
    }
    
    /**
     * reset the ammo count of the gun
     */
    public void resetAmmo() {
        this.ammoCount = this.maxAmmo;
    }
    
    /**
     * update the understanding of the gun
     * @param wc: context of the world to set
     */
    public void updateWorldContext(WorldContext wc) {
        this.wc = wc;
    }
    
    /**
     * owner of the gun
     * @param owner: the owner to set
     */
    public void setOwner(Ship owner) {
        this.owner = owner;
    }

}
