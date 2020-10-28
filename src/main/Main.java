/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import server.Server;
import server.UserListener;
import server.player.PlayerListener;
import server.viewer.ViewerListener;
import world.World;

/**
 * 
 * @author William Kirby
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("CallToThreadRun")
    public static void main(String[] args) {
        try {
            World world = new World();
            List<UserListener> listeners = new ArrayList<>();
            listeners.add(new ViewerListener(world)); // add a viewer listener
            listeners.add(new PlayerListener(world));
            Server server = new Server(world,listeners);
            server.run();
        } catch (IOException ex) {
            System.out.println("something went awfully wrong");
        }
    }
    
}
