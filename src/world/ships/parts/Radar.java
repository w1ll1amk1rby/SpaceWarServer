package world.ships.parts;

import world.ships.Ship;
import java.util.ArrayList;
import java.util.List;
import world.WorldContext;
import world.coords.CoordMath;
import world.coords.ICoord;
import world.ships.Ship;
import world.ships.parts.guns.Projectile;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * radar is used to gather information from the world
 * @author William Kirby
 */
public class Radar {
    
    private WorldContext worldContext; // where the radar exists
    private final ICoord position; // position of the radar
    private final double viewDistance; // how far the radar can see
    
    /**
     * basic constructor
     * @param pos: position of the radar
     * @param viewDistance: distance the radar can see
     */
    public Radar(ICoord pos,double viewDistance) {
        this.position = pos;
        this.viewDistance = viewDistance;
    }
    
    /**
     * get the currently visible ships
     * @return list of ships that are visible
     */
    public List<Ship> getVisibleShips() {
        List<Ship> ships = this.worldContext.getUnmodShips();
        List<Ship> visShips = new ArrayList<>(); // ships that are visible
        for (Ship ship : ships) { // see if the distance between the ships lies within the view range
            double shipDistance = CoordMath.calculateDistance(this.position
                    ,ship.getCentre());
            if(shipDistance <= this.viewDistance) {
                visShips.add(ship);
            }
        }
        return visShips;
    }
    
    /**
     * get the visible projectiles.
     * @return list of projectiles that are visible
     */
    public List<Projectile> getVisibleProjectiles() {
        List<Projectile> projs = this.worldContext.getUnmodProjectiles();
        List<Projectile> visProj = new ArrayList<>();
        for(Projectile proj: projs) {
            double distance = CoordMath.calculateDistance(this.position
                    ,proj.getCentre());
            if(distance <= this.viewDistance) {
                visProj.add(proj);
            }
        }
        return visProj;
    }
    
    /**
     * update the world context of the radar.
     * @param wc: the world context
     */
    public void updateWorldContext(WorldContext wc) {
        this.worldContext = wc;
    }
}