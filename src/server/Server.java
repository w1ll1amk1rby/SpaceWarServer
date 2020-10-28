/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.ArrayList;
import java.util.List;
import AIGenerator.AIGenerator;
import world.World;

/**
 * the server is the centre and handles users 
 * joining the world as well as running the game.
 * @author William Kirby
 */
public class Server {
    
    private final World world; // the world the game exists in
    
    private final boolean shouldKeepRunning; // should the server continue to run
    private final List<User> users; // users running on the server
    private final List<UserListener> listeners; // listeners listen for incoming users
    
    /**
     * basic constructor for the server.
     * @param world: world the server runs
     * @param listeners: listeners of the server
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public Server(World world,List<UserListener> listeners) {
        this.world = world;
        this.users = new ArrayList<>();
        this.listeners = listeners;
        this.shouldKeepRunning = true;
        for(int x = 0;x < this.listeners.size();x++) {
            this.listeners.get(x).setServerToListenFor(this);
        }
        for(int x = 0;x < 10;x++) {
            this.world.addShipToWorld(AIGenerator.generateABasicFighter(),true);
        }
        for(int x = 0;x < 10;x++) {
            this.world.addShipToWorld(AIGenerator.generateABasicCruiser(),true);
        }
        for(int x = 0;x < 10;x++) {
            this.world.addShipToWorld(AIGenerator.generateABasicBattleship(),true);
        }
    }
    
    /**
     * begin running the server.
     */
    public synchronized void run()  {
        
        // start the listener threads 
        for(int x = 0;x < this.listeners.size();x++) {
            this.listeners.get(x).start();
        }
        // loop for the server running
        while(this.shouldKeepRunning) {
            this.world.processWorldTick();
            this.handleUserDisconnects();
        }
        // stop the listener threads
        for(int x = 0;x < this.listeners.size();x++) {
            this.listeners.get(x).stopListening();
            try {
                this.listeners.get(x).join();
            } catch (InterruptedException ex) {
                // just ignore it
            }
        }
        // disconnect remaining users
        for(int x = 0;x < this.users.size();x++) {
            this.users.get(x).handleDisconnect();
        }
    }
    
    /**
     * handle user disconnects from the server
     */
    public void handleUserDisconnects() {
        // see if any of the users have disconnected
        synchronized(this.users) {
            for(int x = 0;x < this.users.size();x++) {
                User user = this.users.get(x);
                if(user.hasDisconnected()) {
                    user.handleDisconnect();
                    this.users.remove(user);
                    x--;
                }
            }
        }
    }
    
    /**
     * add a new user to the server.
     * @param user: user to add to the server.
     */
    protected void addUserToServer(User user) {
        synchronized(this.users) { 
            this.users.add(user);
        }
    }
}
