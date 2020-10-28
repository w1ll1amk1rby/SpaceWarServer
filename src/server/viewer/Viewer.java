/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.viewer;

import java.io.IOException;
import server.User;
import server.messaging.Messenger;
import world.World;

/**
 * 
 * @author William Kirby
 */
public class Viewer extends User implements Runnable {
    
    private final SocketObserver observer;
    private final World world;
    
    /**
     * basic constructor for the viewer
     * @param messenger: messenger the viewer uses to communicate
     * @param world: world the viewer is observing
     */
    private Viewer(Messenger messenger, World world) {
        super(messenger);
        this.world = world;
        this.observer = new SocketObserver(messenger);
        this.world.addObserverToWorld(this.observer); 
    }
    
    /**
     * listen for a disconnect
     */
    @Override
    public void run() {
        while(!this.messenger.isClosed()) {
            try {
                this.messenger.getMessage();
            } catch (IOException ex) {
                this.messenger.close();
            }
        }
    }
    
    /**
     * do everything required to disconnect user from server.
     */
    @Override
    public void handleDisconnect() {
        super.handleDisconnect();
        this.world.removeObserverFromWorld(this.observer);
    }
    
    /**
     * generate a new viewer and start threads (threads close on viewer disconnect)
     * @param messenger: messenger used to communicate to the viewer
     * @param world: world the viewer is observing
     * @return the new viewer.
     */
    public static Viewer generateANewViewer(Messenger messenger, World world) {
        Viewer viewer = new Viewer(messenger,world);
        Thread thread = new Thread(viewer);
        thread.start();
        return viewer;
    }
}
 