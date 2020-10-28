/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.ships;

import world.coords.HistoryCoord;
import world.hitBoxes.CircleHitBox;
import world.ships.parts.guns.ProjectileGun;
import world.ships.parts.Hull;
import world.ships.parts.Radar;
import world.ships.parts.ShipLogic;
import world.ships.parts.Thruster;

/**
 *
 * @author William Kirby
 */
public class Battleship extends Ship {
    
    public final static double RADIUS = 1;
    public final static double MASS = 100;
    public final static double ACCELERATION = 0.05;
    public final static double MAX_THRUST = ACCELERATION*MASS;
    public final static double RADAR_RADIUS = 120;
    public final static int MAX_HEALTH = 100;
    public final static double PROJECTILE_VELOCITY = 30;
    public final static int MAX_GUN_AMMO = 3;
    public final static double RELOAD_AMOUNT = 0.0625;
    
    public Battleship(HistoryCoord centre,ShipLogic shipLogic) {
        super(centre,
                MASS, 
                new CircleHitBox(centre,RADIUS), 
                new Radar(centre,RADAR_RADIUS), 
                new Hull(MAX_HEALTH), 
                shipLogic, 
                new Thruster(MAX_THRUST), 
                new ProjectileGun(PROJECTILE_VELOCITY,MAX_GUN_AMMO,RELOAD_AMOUNT));
    }
    
}
