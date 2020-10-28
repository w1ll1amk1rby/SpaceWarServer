/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.ships;

import world.coords.HistoryCoord;
import world.hitBoxes.CircleHitBox;
import world.ships.parts.guns.LaserGun;
import world.ships.parts.Hull;
import world.ships.parts.Radar;
import world.ships.parts.ShipLogic;
import world.ships.parts.Thruster;

/**
 * 
 * @author William Kirby
 */
public class Cruiser extends Ship {
    
    public final static double RADIUS = 1;
    public final static double MASS = 10;
    public final static double RADAR_RADIUS = 150;
    public final static double GUN_RANGE = 80;
    public final static double ACCELERATION = 0.05;
    public final static double MAX_THRUST = ACCELERATION*MASS;
    public final static int MAX_HEALTH = 40;
    public final static int GUN_DAMAGE = 1;
    public final static int MAX_GUN_AMMO = 20;
    public final static double RELOAD_AMOUNT = 0.2;
    
    public Cruiser(HistoryCoord centre,ShipLogic shipLogic) {
        super(centre,
                MASS, 
                new CircleHitBox(centre,RADIUS), 
                new Radar(centre,RADAR_RADIUS), 
                new Hull(MAX_HEALTH), 
                shipLogic, 
                new Thruster(MAX_THRUST), 
                new LaserGun(GUN_DAMAGE,GUN_RANGE,MAX_GUN_AMMO,RELOAD_AMOUNT)
        );
    }
    
}
