/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.player.shipLogic.messages;

import server.messaging.SendableMessage;
import world.ships.parts.guns.Projectile;

/**
 *
 * @author William Kirby
 */
public class AddNewProjectileMessage implements SendableMessage {

    public AddNewProjectileMessage(Projectile get) {
    }

    @Override
    public String messageToString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
