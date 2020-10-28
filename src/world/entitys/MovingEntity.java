/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.entitys;

import world.coords.HistoryCoord;
import world.coords.ICoord;

/**
 * an entity that is in a state of motion
 * @author William Kirby
 */
public abstract class MovingEntity extends Entity {
    
    protected double velocityX,velocityY; // velocity in the axises
    protected double lastVelocityX,lastVelocityY; // velocity in the last axis
    protected final HistoryCoord historyCoord; // the history coord of the entity
    
    /**
     * basic constructor for a moving entity
     * @param centre: the centre of the entity
     */
    public MovingEntity(HistoryCoord centre) {
        super(centre);
        this.historyCoord = centre;
    }
    
    /**
     * enact motion on to the object. 
     * adds the velocity to the current values.
     */
    public void enactMotion() {
        this.lastVelocityX = this.velocityX;
        this.lastVelocityY = this.velocityY;
        this.centre.setX(this.centre.getX() + this.velocityX);
        this.centre.setY(this.centre.getY() + this.velocityY);
    }
    
    /**
     * retrieve the velocity in the x axis
     * @return the velocity in the x axis.
     */
    public double getVelocityX() {
        return this.velocityX;
    }
    
    /**
     * set the velocity in the x axis. 
     * @param velocityX: the velocity to set in the x.
     */
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
        this.lastVelocityX = velocityX;
    }
    
    /**
     * retrieve the velocity in the y axis
     * @return the velocity in the y axis.
     */
    public double getVelocityY() {
        return this.velocityY;
    }
    
    /**
     * set the velocity in the y axis. 
     * @param velocityY: the velocity to set in the y.
     */
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
        this.lastVelocityY = velocityY;
    }
    
    /**
     * apply velocity on the object
     * @param velocityX: acceleration in the x axis
     * @param velocityY: acceleration in the y axis
     */
    public void applyVelocity(double velocityX,double velocityY) {
        this.applyVelocityX(velocityX);
        this.applyVelocityY(velocityY);
    }
    
    /**
     * apply velocity on object in x axis
     * @param velocityX: acceleration in the x axis.
     */
    public void applyVelocityX(double velocityX) {
        this.velocityX = this.velocityX + velocityX;
    }
    
    /**
     * apply velocity on object in y axis
     * @param velocityY acceleration in the y axis.
     */
    public void applyVelocityY(double velocityY) {
        this.velocityY = this.velocityY + velocityY;
    }
    
    /**
     * retrieve the centre of the entity.
     * @return the centre
     */
    @Override
    public HistoryCoord getCentre() {
        return this.historyCoord;
    }
    
    /**
     * t = 0 was last position while t = 1 is current position
     * @param entity: to test against
     * @return the time in last move that the two objects were closest.
     */
    public double getTimeOfClosestApproachFromLast(MovingEntity entity) {
        // get the last position to test (at time 0);
        ICoord thisCoord = this.historyCoord.getLastPosition();
        ICoord otherCoord = entity.historyCoord.getLastPosition();
        // this uses the solution to the two boat problem that can be found here
        // https://www.youtube.com/watch?v=g-C83VyloMM
        // the coordinates for each ship for a given time can be found with 
        // these equations
        //x and y are coords while vx and vy are velocities
        // x1(t) = x1 + vx1t // y1(t) = y1 + vx1t
        // x2(t) = x2 + vx2t // y2(t) = y2 + vx2t
        // from this we can find that the distance at a given time comes from the equation
        // distance = Math.sqrt((x1(t) - x2(t))^2 + (y1(t) - y2(t))^2);
        // further we can find that
        // distance^2 = (x1(t) - x2(t))^2 + (y1(t) - y2(t))^2
        // replacing (t) functions we can evetually make the equation look like this
        // where
        // diffx = x1 - x2
        double diffx = thisCoord.getX() - otherCoord.getX();
        // diffy = y1 - y2
        double diffy = thisCoord.getY() - otherCoord.getY();
        // diffvx = vx1 - vx2
        double diffvx = this.lastVelocityX - entity.lastVelocityX;
        // diffvy = vy1 - vy2
        double diffvy = this.lastVelocityY - entity.lastVelocityY;
        // A = (diffvy^2 + diffvx^2)
        double a = (diffvy*diffvy) + (diffvx*diffvx);
        // B = (diffy*diffvy + diffx*diffvx)
        double b = (diffy*diffvy) + (diffx*diffvx);
        // C = (diffx^2 + diffy^2)
        // distance^2 = At^2 + 2Bt + C
        // this provides a parabola graph where the turning point of the parabola is the shortest distance from each other
        // from this we can gather the time where the ships were closest
        // to do this we use differentiation to find the gradient at a given time
        // gradient = 2At + 2B
        // when the gradient = 0 we want time t
        // 0 = 2At + 2B
        // t = -2B/2A
        // t = -B/A
        double t;
        if(a == 0) {
            t = 0;
        }
        else {
            t = -b/a;
        }
        // as we only want to test for times between 0 and 1 
        // force t in to range
        if(t < 0) t = 0;
        else if(t > 1) t = 1;
        return t;
    }
}
