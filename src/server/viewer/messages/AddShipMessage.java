/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.viewer.messages;

import server.messaging.SendableMessage;
import world.coords.ICoord;
import world.ships.Battleship;
import world.ships.Cruiser;
import world.ships.Fighter;
import world.ships.Ship;

/**
 * add a ship to the view message
 * @author William Kirby
 */
public class AddShipMessage implements SendableMessage {
    
    private final Ship ship; // the ship to add
    
    private final static String PREFIX = "ADD_SHIP";
    
    /**
     * basic constructor 
     * @param ship: the ships details
     */
    public AddShipMessage(Ship ship) {
        this.ship = ship;
    }
    
    /**
     * convert the add ship message in to a string to be sent across a port
     * @return the message as a string
     */
    @Override
    public String messageToString() {
        ICoord coord = this.ship.getCentre();
        return PREFIX + " "
                + this.getShipID() + " "
                + this.ship.getID() + " " 
                + (int) coord.getX() + " " 
                + (int) coord.getY();
    }
    
    /**
     * get the type of ship ready for display on the viewer
     * @return the type of ship
     */
    private String getShipID() {
        if(this.ship instanceof Fighter) {
            return "0";
        }
        else if(this.ship instanceof Cruiser) {
            return "1";
        }
        else if(this.ship instanceof Battleship) {
            return "2";
        }
        else {
            throw new UnsupportedOperationException();
        }
    }
}
