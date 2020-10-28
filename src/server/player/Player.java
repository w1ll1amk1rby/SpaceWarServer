/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.player;

import server.player.shipLogic.PlayerShipLogic;
import server.User;
import server.messaging.Messenger;
import world.World;
import world.coords.HistoryCoord;
import world.ships.Fighter;

/**
 * a player has the ability to play on the server
 * @author William Kirby
 */
public class Player extends User implements Runnable {

    private final World world;
    
    /**
     * basic player constructor
     * @param messenger: used to communicate to the player
     * @param world: the world the player should join
     */
    private Player(Messenger messenger,World world) {
        super(messenger);
        this.world = world;
    }
    
    /**
     * listen to the Player and add ship to world.
     */
    @Override
    public void run() {
        this.world.addShipToWorld(new Fighter(new HistoryCoord(0,0),new PlayerShipLogic(this.messenger)), true);
    }
    
    /**
     * generate a new viewer and start threads (threads close on viewer disconnect)
     * @param messenger: messenger used to communicate to the player
     * @param world: world that the player will interact with
     * @return the new player.
     */
    public static Player generateANewPlayer(Messenger messenger,World world) {
        Player player = new Player(messenger,world);
        Thread thread = new Thread(player);
        thread.start();
        return player;
    }
    
}
