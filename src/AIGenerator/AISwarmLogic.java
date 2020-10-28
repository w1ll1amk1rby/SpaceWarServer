/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AIGenerator;

import java.util.ArrayList;
import java.util.List;
import world.coords.Coord;
import world.coords.CoordMath;
import world.coords.ICoord;
import world.ships.Ship;
import world.ships.parts.ShipLogic;
import world.ships.parts.guns.Projectile;

/**
 *
 * @author William Kirby
 */
public class AISwarmLogic extends ShipLogic {
    
    private final List<Ship> allies;
    
    public AISwarmLogic() {
        this.allies = new ArrayList<>();
    }
    
    @Override
    public void calculateNextMove(Ship ownedShip, List<Ship> ships,List<Projectile> projectiles) {
        // check if any allies are dead forever
        for(int x = 0;x < this.allies.size();x++) {
            Ship ship = this.allies.get(x);
            if(ship.getHull().isHealthCritical() && !ship.shouldRespawn()) {
                this.allies.remove(ship);
                x--;
            }
        }
        // get the target
        ships.removeAll(this.allies);
        Ship closestShip = this.getClosestShip(ships, ownedShip);
        if(closestShip != null) {
            this.targetCoord = closestShip.getCentre();
        }
        // find the average coord
        Coord averageCoord;
        if(!this.allies.isEmpty()) {
            double xtotal = 0;
            double ytotal = 0;
            for(int x = 0;x < this.allies.size();x++) {
                ICoord coord = this.allies.get(x).getCentre();
                xtotal = xtotal + coord.getX();
                ytotal = ytotal + coord.getY();
            }
            averageCoord = new Coord(xtotal/this.allies.size(),ytotal/this.allies.size());
        }
        else {
            averageCoord = new Coord(500,500);
        }
        // if a ship around then burn towards that ship
        if(!ships.isEmpty()) {
            double newX = (averageCoord.getX() + closestShip.getCentre().getX())/2;
            double newY = (averageCoord.getY() + closestShip.getCentre().getY())/2;
            averageCoord.setX(newX);
            averageCoord.setY(newY);
        }
        
        //burn towards averagecoord
        double angle = CoordMath.getAngleFrom(ownedShip.getCentre(), averageCoord);
        this.thrustDirection = angle;
        this.thrustAmount = ownedShip.getThruster().getMaxThrust();
    }

    @Override
    public void informOfDeath() {
        // do nothing
    }
    
    /**
     * add an allie to the ships logic
     * @param ship: ship that will no longer be targeted
     */
    public void addAllie(Ship ship) {
        this.allies.add(ship);
    }
    
    private Ship getClosestShip(List<Ship> ships,Ship ship) {
        Ship closest;
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
}
