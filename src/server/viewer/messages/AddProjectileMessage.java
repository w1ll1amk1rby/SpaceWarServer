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
public class AddProjectileMessage implements SendableMessage {

    
    private final static String PREFIX = "ADD_PROJECTILE";
    private final Projectile projectile;
    
    /**
     * basic constructor 
     * @param projectile
     */
    public AddProjectileMessage(Projectile projectile) {
        this.projectile = projectile;
    }
    
    /**
     * convert the add ship message in to a string to be sent across a port
     * @return the message as a string
     */
    @Override
    public String messageToString() {
        ICoord coord = this.projectile.getCentre();
        return PREFIX + " "
                + this.projectile.getID() + " " 
                + coord.getX() + " " 
                + coord.getY();
    }
}
