/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.viewer.messages;

import server.messaging.SendableMessage;
import world.coords.ICoord;
import world.ships.Ship;

/**
 *
 * @author William Kirby
 */
public class UpdateShipMessage implements SendableMessage {

    private final Ship ship;
    private final static String PREFIX = "UPDATE_SHIP";
    
    /**
     * basic constructor for update ship message
     * @param ship: ship to update
     */
    public UpdateShipMessage(Ship ship) {
        this.ship = ship;
    }

    @Override
    public String messageToString() {
        ICoord coord = this.ship.getCentre();
        return PREFIX + " "
                + this.ship.getID() + " " 
                + (int) coord.getX() + " " 
                + (int) coord.getY();
    }
    
}
