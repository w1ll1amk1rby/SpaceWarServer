package server;

import server.messaging.Messenger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author William Kirby
 */
public class User {

    protected final Messenger messenger;
    
    /**
     * basic constructor for a user.
     * @param messenger: used to communicate to user
     */
    public User(Messenger messenger) {
        this.messenger = messenger;
    }
    
    /**
     * test if the user disconnected from the server
     * @return true if the user has disconnected
     */
    public boolean hasDisconnected() {
        return this.messenger.isClosed();
    }
    
    /**
     * disconnect the user from the server
     */
    public void handleDisconnect() {
        this.messenger.close();
    }
    
}
