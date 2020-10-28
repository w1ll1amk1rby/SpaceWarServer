/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.viewer.messages;

import server.messaging.SendableMessage;
import world.ships.parts.guns.Laser;

/**
 *
 * @author William Kirby
 */
public class RemoveLaserMessage implements SendableMessage {

    private final Laser laser;
    private final static String PREFIX = "REMOVE_LASER";
    
    /**
     * basic constructor
     * @param laser: laser to remove
     */
    public RemoveLaserMessage(Laser laser) {
        this.laser = laser;
    }
    
    /**
     * convert the message in to as string to send across ports
     * @return message as a string
     */
    @Override
    public String messageToString() {
        return PREFIX + " " 
                + this.laser.getID();
    }
    
}
