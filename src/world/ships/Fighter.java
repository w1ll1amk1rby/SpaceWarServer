/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.ships;

import world.coords.HistoryCoord;
import world.ships.parts.Radar;
import world.ships.Ship;
import world.ships.parts.Hull;
import world.hitBoxes.CircleHitBox;
import world.ships.parts.guns.LaserGun;
import world.ships.parts.ShipLogic;
import world.ships.parts.Thruster;


/**
 * a fighter ship class that has a laser gun and high acceleration
 * @author William Kirby
 */
public class Fighter extends Ship {
    
    public final static double RADIUS = 0.2;
    public final static double MASS = 1;
    public final static double ACCELERATION = 0.2;
    public final static double MAX_THRUST = ACCELERATION*MASS;
    public final static double RADAR_RADIUS = 100;
    public final static int MAX_HEALTH = 20;
    
    public final static int GUN_DAMAGE = 2;
    public final static double GUN_RANGE = 50;
    public final static int MAX_GUN_AMMO = 1;
    public final static double RELOAD_AMOUNT = 1.0;
   
    
    /**
     * basic constructor for a fighter
     * @param centre: the position of the fighter.
     * @param fighterLogic: logic that the fighter uses
     */
    public Fighter(HistoryCoord centre,ShipLogic fighterLogic) {
        super(centre, 
                MASS, 
                new CircleHitBox(centre,RADIUS), 
                new Radar(centre,RADAR_RADIUS), 
                new Hull(MAX_HEALTH),
                fighterLogic,
                new Thruster(MAX_THRUST),
                new LaserGun(GUN_DAMAGE,GUN_RANGE,MAX_GUN_AMMO,RELOAD_AMOUNT)
        );
    }
}
