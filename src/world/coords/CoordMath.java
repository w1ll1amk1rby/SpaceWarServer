/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.coords;

/**
 * general math between two coords
 * @author William Kirby
 */
public class CoordMath {
    
    /**
     * calculate distance between two coords
     * @param coord1: the first coordinate
     * @param coord2: the second coordinate
     * @return the distance between the two points
     */
    public static double calculateDistance(ICoord coord1, ICoord coord2) {
        double distanceX =  coord1.getX() -  coord2.getX();
        double distanceY =  coord1.getY() -  coord2.getY();
        return Math.sqrt((distanceX*distanceX) + (distanceY*distanceY));
    }
    
    /**
     * calculate the angle from coord1 to coord2 (angle measured from positive y)
     * @param coord1: coord you are measuring from
     * @param coord2: coord measuring too.
     * @return the angle from coord1 to coord2 in degrees
     */
    public static double getAngleFrom(ICoord coord1,ICoord coord2) {
            double differenceX = coord2.getX() - coord1.getX();
            double differenceY = coord2.getY() - coord1.getY();
            return Math.toDegrees(Math.atan2(differenceX,differenceY));
    }
    
    
}
