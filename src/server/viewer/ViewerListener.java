/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.viewer;

import java.io.IOException;
import server.UserListener;
import server.messaging.Messenger;
import world.World;

/**
 * a viewer listener listens for incoming viewers
 * @author William Kirby
 */
public class ViewerListener extends UserListener {
    
    private static final int PORT = 2903; // port to listen for viewers on
    
    private final World world; // world to add observers too
    
    /**
     * basic constructor for a viewerListener
     * @param world: world that the viewers want to watch
     * @throws IOException: 
     */
    public ViewerListener(World world) throws IOException {
        super(PORT);
        this.world = world;
    }
    
    /**
     * generate a viewer from the messenger.
     * @param messenger: the messenger used to communicate with the user
     * @return the new Viewer from the messenger
     */
    @Override
    public Viewer generateUser(Messenger messenger) {
        return Viewer.generateANewViewer(messenger,this.world);
    }
    
}
