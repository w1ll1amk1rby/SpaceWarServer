/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.viewer;

import server.viewer.messages.*;
import java.io.IOException;
import server.messaging.Messenger;
import world.observers.Observer;
import world.ships.Ship;
import world.ships.parts.guns.Laser;
import world.ships.parts.guns.Projectile;

/**
 *
 * @author William Kirby
 */
public class SocketObserver extends Observer {
    
    private final Messenger messenger; // messenger to send observer changes
    
    /**
     * basic constructor for a socketObserver
     * @param messenger: messenger to communicate with other user
     */
    public SocketObserver(Messenger messenger) {
        this.messenger = messenger;
    }
    
    /**
     * add a ship to the view
     * @param ship: ship to add to the view
     */
    @Override
    public void addShipToView(Ship ship) {
        try {
            this.messenger.addMessage(new AddShipMessage(ship));
            this.messenger.flushMessages();
        } catch (IOException ex) {
            this.messenger.close();
        }
    }
    
    /**
     * remove a ship from the view
     * @param ship: ship to remove from the view
     */
    @Override
    public void removeShipFromView(Ship ship) {
        try {
            this.messenger.addMessage(new RemoveShipMessage(ship));
            this.messenger.flushMessages();
        } catch (IOException ex) {
            this.messenger.close();
        }
    }
    
    /**
     * update a ships data on the view
     * @param ship: update a ship on the view
     */
    @Override
    public void updateShip(Ship ship) {
        try {
            this.messenger.addMessage(new UpdateShipMessage(ship));
            this.messenger.flushMessages();
        } catch (IOException ex) {
            this.messenger.close();
        }
    }
    
    /**
     * send a new laser over the socket
     * @param laser: laser to add to the view
     */
    @Override
    public void addLaserToView(Laser laser) {
        try {
            this.messenger.addMessage(new AddLaserMessage(laser));
            this.messenger.flushMessages();
        } catch (IOException ex) {
            this.messenger.close();
        }
    }
    
    /**
     * send a request to remove the laser from the view
     * @param laser: laser to remove
     */
    @Override
    public void removeLaserFromView(Laser laser) {
        try {
            this.messenger.addMessage(new RemoveLaserMessage(laser));
            this.messenger.flushMessages();
        } catch (IOException ex) {
            this.messenger.close();
        }
    }
    
    /**
     * send a new projectile message to the socket observer
     * @param projectile: projectile to send
     */
    @Override
    public void addProjectileToView(Projectile projectile) {
        try {
            this.messenger.addMessage(new AddProjectileMessage(projectile));
            this.messenger.flushMessages();
        } catch (IOException ex) {
            this.messenger.close();
        }
    }

    @Override
    public void updateProjectile(Projectile projectile) {
        try {
            this.messenger.addMessage(new UpdateProjectileMessage(projectile));
            this.messenger.flushMessages();
        } catch (IOException ex) {
            this.messenger.close();
        }
    }
    
    @Override
    public void removeProjectile(Projectile projectile) {
        try {
            this.messenger.addMessage(new RemoveProjectileMessage(projectile));
            this.messenger.flushMessages();
        } catch (IOException ex) {
            this.messenger.close();
        }
    }
}
