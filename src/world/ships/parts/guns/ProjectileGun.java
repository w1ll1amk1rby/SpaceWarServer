/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.ships.parts.guns;

import world.coords.CoordMath;
import world.coords.HistoryCoord;
import world.coords.ICoord;

/**
 *
 * @author William Kirby
 */
public class ProjectileGun extends Gun {

    private final double exitVelocity; // velocity
    
    /**
     * basic constructor for a projectile gun
     * @param exitVelocity: the velocity the projectile should exit the gun
     * @param maxAmmo: the max amount of projectiles the gun can hold
     * @param reloadAmount: the amount of projectiles you should gain a turn
     */
    public ProjectileGun(double exitVelocity, int maxAmmo,double reloadAmount) {
        super(maxAmmo,reloadAmount);
        this.exitVelocity = exitVelocity;
    }
    
    @Override
    protected void fire(ICoord targetCoord) {
        double angle = CoordMath.getAngleFrom(this.owner.getCentre(), targetCoord);
        // get velocities for each axis
        double velocityX = Math.sin(Math.toRadians(angle))*this.exitVelocity;
        double velocityY = Math.cos(Math.toRadians(angle))*this.exitVelocity;
        velocityX = velocityX + this.owner.getVelocityX(); 
        velocityY = velocityY + this.owner.getVelocityY();
        // get the starting position
        ICoord centre = this.owner.getCentre();
        HistoryCoord coord = new HistoryCoord(centre.getX(),centre.getY());
        // generate the projectile
        Projectile projectile = new Projectile(coord,this.owner,this.wc);
        this.wc.addProjectileToWorld(projectile);
        projectile.setVelocityX(velocityX);
        projectile.setVelocityY(velocityY);
    }
    
}
