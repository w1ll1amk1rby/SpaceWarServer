package world.hitBoxes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import world.coords.CoordMath;

/**
 * test if hit boxes are touching
 * @author William Kirby
 */
public class CollisionDetection {
    
    /**
     * test if a sphere and an unspecified hitbox are touching
     * @param sphere: sphere to test
     * @param hitBox: unspecified to test
     * @return if they are touching
     */
    protected static boolean isTheCircleTouchingTheHitBox(CircleHitBox sphere,HitBox hitBox) {
        if(hitBox instanceof CircleHitBox) {
            return isCircleTouchingCircle(sphere,(CircleHitBox) hitBox);
        }
        else {
            throw new UnsupportedOperationException();
        }
    }
    
    /**
     * test if two sphere hitboxes are touching
     * @param sphere1: the first sphere
     * @param sphere2: the second sphere
     * @return if they are touching
     */
    public static boolean isCircleTouchingCircle(CircleHitBox sphere1,CircleHitBox sphere2) {
        double distance = CoordMath.calculateDistance(sphere1.getCentre(), sphere2.getCentre());
        return distance <= (sphere1.getRadius() + sphere2.getRadius());
    }
    
}
