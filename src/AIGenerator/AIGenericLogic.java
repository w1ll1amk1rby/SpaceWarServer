/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AIGenerator;

import java.util.List;
import world.World;
import world.coords.Coord;
import world.coords.CoordMath;
import world.coords.ICoord;
import world.ships.Ship;
import world.ships.parts.guns.LaserGun;
import world.ships.parts.ShipLogic;
import world.ships.parts.guns.Projectile;

/**
 * 
 * @author William Kirby
 */
class AIGenericLogic extends ShipLogic {
    
    /**
     * calculate the next move
     * @param ownedShip: ship that is owned 
     * @param ships: ships that are in view of the ship
     */
    @Override
    public void calculateNextMove(Ship ownedShip, List<Ship> ships,List<Projectile> projectiles) {
        // find the closest ship
        if(!ships.isEmpty()) {
            ICoord myCoord = ownedShip.getCentre();
            ICoord coord = this.getClosestShip(ships, ownedShip).getCentre();
            this.thrustDirection = CoordMath.getAngleFrom(myCoord, coord);
            this.thrustAmount = ownedShip.getThruster().getMaxThrust();
        }
        else { // go to the center
            ICoord myCoord = ownedShip.getCentre();
            ICoord coord = new Coord(World.MAX_WIDTH/2,World.MAX_HEIGHT/2); 
            this.thrustDirection = CoordMath.getAngleFrom(myCoord, coord);
            this.thrustAmount = ownedShip.getThruster().getMaxThrust();
        }
        // target ship
        if(ships.isEmpty()) {
            this.targetCoord = null;
        }
        else {
            if(ownedShip.getGun() instanceof LaserGun) {
                LaserGun gun = (LaserGun) ownedShip.getGun();
                ICoord closestShip = this.getClosestShip(ships, ownedShip).getCentre();
                double distance = CoordMath.calculateDistance(ownedShip.getCentre(), closestShip);
                if(distance <= gun.getRange()) {
                    this.targetCoord = closestShip;
                }
                else {
                    this.targetCoord = null;
                }
            }
            else {
                this.targetCoord = this.getClosestShip(ships, ownedShip).getCentre();
            }
        }
    }
    
    private Ship getClosestShip(List<Ship> ships,Ship ship) {
        Ship closest = null;
        double distance = 0;
        if(ships.isEmpty()) {
            return null;
        }
        closest = ships.get(0);
        distance = CoordMath.calculateDistance(ship.getCentre(),ships.get(0).getCentre());
        // find if any ship is closer
        for(int x = 1;x < ships.size();x++) {
            double testDistance = CoordMath.calculateDistance(ship.getCentre(),ships.get(x).getCentre());
            if(distance > testDistance) {
                closest = ships.get(x);
                distance = testDistance;
            }
        }
        return closest;
    }
    
    /**
     * inform the ship logic of death.
     */
    @Override
    public void informOfDeath() {
    
    }
}
