/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AIGenerator;

import java.util.ArrayList;
import java.util.List;
import world.coords.HistoryCoord;
import world.ships.Battleship;
import world.ships.Cruiser;
import world.ships.Fighter;
import world.ships.Ship;
import world.World;

/**
 * used to generate different AI for the game
 * @author William Kirby
 */
public class AIGenerator {
    
    
    /**
     * generate a new basic fighter
     * @return the new fighter
     */
    public static Fighter generateABasicFighter() {
        return new Fighter(new HistoryCoord(0,0),new AIGenericLogic());
    }
    
    /**
     * generate a new basic cruiser
     * @return the new cruiser
     */
    public static Cruiser generateABasicCruiser() {
        return new Cruiser(new HistoryCoord(0,0),new AIGenericLogic());
    }
    
    /**
     * generate a basic battleship
     * @return a new battleship
     */
    public static Ship generateABasicBattleship() {
        return new Battleship(new HistoryCoord(0,0),new AIGenericLogic());
    }
    
    public static List<Ship> generateAFighterSwarm(int swarmSize) {
        AISwarmLogic[] logics = new AISwarmLogic[swarmSize];
        List<Ship> ships = new ArrayList<>();
        
        for(int x = 0;x < logics.length;x++) {
            logics[x] = new AISwarmLogic();
            Ship ship = new Fighter(generateASwarmCoord(),logics[x]);
            ship.setRespawn(false);
            ships.add(ship);
        }
        
        // add the ships to each others allies
        for(int x = 0;x < logics.length;x++) {
            for(int i = 0;i < ships.size();i++) {
                logics[x].addAllie(ships.get(i));
            }
        }
        return ships;
    }

    private static HistoryCoord generateASwarmCoord() {
        double x = World.MAX_WIDTH/2 + (Math.random()*100) - 50;
        double y = World.MAX_HEIGHT/2 + (Math.random()*100) - 50;
        return new HistoryCoord(x,y);
        
    }
    
}
