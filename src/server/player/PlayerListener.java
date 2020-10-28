/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.player;

import java.io.IOException;
import server.User;
import server.UserListener;
import server.messaging.Messenger;
import world.World;

/**
 * listens for players wanting to connect to the server
 * @author William Kirby
 */
public class PlayerListener extends UserListener {
    
    private static final int PORT = 2904; // port to listen for viewers on
    private final World world; // world the player wants to join
    
    public PlayerListener(World world) throws IOException {
        super(PORT);
        this.world = world;
    }
    
    /**
     * generate a user from the messenger
     * @param messenger: messenger to generate for the user
     * @return the created user.
     */
    @Override
    protected User generateUser(Messenger messenger) {
        return Player.generateANewPlayer(messenger,this.world);
    }
}
