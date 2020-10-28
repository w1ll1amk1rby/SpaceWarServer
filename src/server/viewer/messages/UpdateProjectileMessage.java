/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.viewer.messages;

import server.messaging.SendableMessage;
import world.coords.ICoord;
import world.ships.parts.guns.Projectile;

/**
 *
 * @author William Kirby
 */
public class UpdateProjectileMessage implements SendableMessage {
    
    private final Projectile projectile;
    private final static String PREFIX = "UPDATE_PROJECTILE";
    
    /**
     * basic constructor for update ship message
     * @param projectile: projectile to update
     */
    public UpdateProjectileMessage(Projectile projectile) {
        this.projectile = projectile;
    }

    @Override
    public String messageToString() {
        ICoord coord = this.projectile.getCentre();
        return PREFIX + " "
                + this.projectile.getID() + " " 
                + coord.getX() + " " 
                + coord.getY();
    }
}
