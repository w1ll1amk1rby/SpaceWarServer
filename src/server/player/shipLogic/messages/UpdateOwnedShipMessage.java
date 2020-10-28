/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.player.shipLogic.messages;

import server.messaging.SendableMessage;
import world.ships.Ship;

/**
 *
 * @author William Kirby
 */
public class UpdateOwnedShipMessage implements SendableMessage {

    public UpdateOwnedShipMessage(Ship ownedShip) {
        
    }

    @Override
    public String messageToString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
