/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.viewer.messages;

import server.messaging.SendableMessage;
import world.ships.parts.guns.Projectile;

/**
 *
 * @author William Kirby
 */
public class RemoveProjectileMessage implements SendableMessage {

    private final Projectile projectile;
    private final static String PREFIX = "REMOVE_PROJECTILE";
    
    /**
     * basic constructor
     * @param projectile: projectile to remove
     */
    public RemoveProjectileMessage(Projectile projectile) {
        this.projectile = projectile;
    }
    
    /**
     * convert the message in to as string to send across ports
     * @return message as a string
     */
    @Override
    public String messageToString() {
        return PREFIX + " " 
                + this.projectile.getID();
    }
}
