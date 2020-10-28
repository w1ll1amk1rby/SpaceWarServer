/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.viewer.messages;

import server.messaging.SendableMessage;
import world.ships.Ship;

/**
 * remove a ship message to send across messengers
 * @author William Kirby
 */
public class RemoveShipMessage implements SendableMessage {
    
    private final Ship ship;
    private final static String PREFIX = "REMOVE_SHIP";
    
    /**
     * basic constructor
     * @param ship: ship to remove
     */
    public RemoveShipMessage(Ship ship) {
        this.ship = ship;
    }
    
    /**
     * convert the message in to as string to send across ports
     * @return message as a string
     */
    @Override
    public String messageToString() {
        return PREFIX + " " 
                + this.ship.getID();
    }
    
}
