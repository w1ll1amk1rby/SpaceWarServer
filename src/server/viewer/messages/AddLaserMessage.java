/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.viewer.messages;

import server.messaging.SendableMessage;
import world.coords.ICoord;
import world.ships.parts.guns.Laser;

/**
 *
 * @author William Kirby
 */
public class AddLaserMessage implements SendableMessage {

    private final Laser laser; // the laser to add
    
    private final static String PREFIX = "ADD_LASER";
    
    /**
     * basic constructor 
     * @param laser: the lasers details
     */
    public AddLaserMessage(Laser laser) {
        this.laser = laser;
    }
    
    /**
     * convert the add laser message in to a string to be sent across a port
     * @return the message as a string
     */
    @Override
    public String messageToString() {
        ICoord coord = this.laser.getTarget();
        return PREFIX + " "
                + this.laser.getID() + " " 
                + this.laser.getOwner().getID() + " "
                + (int)coord.getX() + " " 
                + (int)coord.getY();
    }
    
}
