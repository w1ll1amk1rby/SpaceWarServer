/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.player.shipLogic;

import server.player.shipLogic.messages.RequestNextAction;
import server.player.shipLogic.messages.AddNewProjectileMessage;
import server.player.shipLogic.messages.AddNewShipMessage;
import server.player.shipLogic.messages.UpdateOwnedShipMessage;
import server.player.shipLogic.messages.ClearUnderstandingMessage;
import java.io.IOException;
import java.util.List;
import server.messaging.Messenger;
import world.ships.Ship;
import world.ships.parts.ShipLogic;
import world.ships.parts.guns.Projectile;

/**
 * a player ship logic where the data is sent across a socket to a player
 * @author William Kirby
 */
public class PlayerShipLogic extends ShipLogic {

    private final Messenger messenger;
    
    /**
     * 
     * @param messenger: the messenger used to communicate to the player
     */
    public PlayerShipLogic(Messenger messenger) {
        this.messenger = messenger;
        this.messenger.setMessageCreator(new PlayerMessageCreator());
    }

    @Override
    public void calculateNextMove(Ship ownedShip, List<Ship> ships, List<Projectile> projectiles) {
        try {
            this.messenger.addMessage(new ClearUnderstandingMessage());
            this.messenger.addMessage(new UpdateOwnedShipMessage(ownedShip));
            for(int x = 0;x < ships.size();x++) {
                this.messenger.addMessage(new AddNewShipMessage(ships.get(x)));
            }
            for(int x = 0;x < projectiles.size();x++) {
                this.messenger.addMessage(new AddNewProjectileMessage(projectiles.get(x)));
            }
            this.messenger.addMessage(new RequestNextAction());
//            // send message and get response
//            Runnable runnable = () -> {
//                this.messenger.flushMessages();  
//                try {
//                    IncomingMessage message = this.messenger.getMessage();
//                } catch (IOException ex) {
//                    // doNothing
//                }
//            };
//            Thread thread = new Thread(runnable);
//            thread.start();
            
        } catch (IOException ex) {
            this.messenger.close();
        }
    }
 
    @Override
    public void informOfDeath() {
        
    }
    
}
