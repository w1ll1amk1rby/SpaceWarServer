/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import server.messaging.Messenger;
import server.messaging.ServerMessengerListener;

/**
 * listen for incoming users
 * @author William Kirby
 */
public class UserListener extends Thread {
    
    private final ServerMessengerListener messengerListener; // used to get 
    // messengers from incoming connections
    private Server server; // server to add users too.
    private boolean continueListening;
    
    /**
     * basic constructor for a user listener
     * @param port: port to listen on
     * @throws java.io.IOException: if given port is already in use.
     */
    public UserListener(int port) throws IOException {
        this.messengerListener = new ServerMessengerListener(port);
        this.continueListening = true;
    }
    
    /**
     * wait for connections until told otherwise.
     */
    @Override
    public void run() {
        while(this.continueListening) {
            Messenger messenger;
            try {
                messenger = this.messengerListener.waitForNextConnection();
                this.server.addUserToServer(this.generateUser(messenger));
            } catch (IOException ex) {
                // doNothing
            }
        }
        this.messengerListener.close();
    }
    
    /**
     * generate a user from the messenger
     * @param messenger: messenger to generate for the user
     * @return the created user.
     */
    protected User generateUser(Messenger messenger) {
        return new User(messenger);
    }
    
    /**
     * set the server the listener is listening for
     * @param server: server to set
     */
    public void setServerToListenFor(Server server) {
        this.server = server;
    }
    
    /**
     * tell listener to stop listening for new connections. will end the thread
     */
    public void stopListening() {
        this.continueListening = false;
        this.messengerListener.close();
    }
}
