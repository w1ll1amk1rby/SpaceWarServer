package world.hitBoxes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import world.coords.Coord;
import world.coords.ICoord;

/**
 * a hitBox that is a circle
 * @author William Kirby
 */
public class CircleHitBox implements HitBox {
    
    private final ICoord centre; // center of the circle.
    private final double radius; // radius of the circle.
    
    /**
     * basic constructor
     * @param centre: centre of the circle.
     * @param radius: radius of the circle.
     */
    public CircleHitBox(ICoord centre,double radius) {
        this.centre = centre;
        this.radius = radius;
    }
    
    /**
     * test if a given coordinate lies within the hitbox
     * @param coord: the coord to check
     * @return if the hitbox contains the point
     */
    @Override
    public boolean containsCoord(ICoord coord) { // check if point lies within the circle.
        double xDistance = this.centre.getX() - coord.getX();
        double yDistance = this.centre.getY() - coord.getY();
        double distance = Math.sqrt((xDistance*xDistance) + (yDistance*yDistance));
        return distance <= this.radius;
    }
    
    /**
     * find if the hitbox is touching this hitbox.
     * @param hitBox: hitbox to test
     * @return if the spherical hitbox is touching this hitbox
     */
    @Override
    public boolean isTouching(HitBox hitBox) {
        return CollisionDetection.isTheCircleTouchingTheHitBox(this, hitBox);
    }
    
    /**
     * get radius of the CircleHitBox
     * @return the radius of the circle
     */
    public double getRadius() {
        return this.radius;
    }
    
    /**
     * get the centre coords of the hitBox
     * @return the centre coord
     */
    public ICoord getCentre() {
        return this.centre;
    }

    /**
     * recreate the circle Hitbox at a new position
     * @param coord: the new coord
     * @return the new circle hit box
     */
    public CircleHitBox recreate(Coord coord) {
        return new CircleHitBox(coord,this.radius);
    }
}