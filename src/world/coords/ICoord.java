/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.coords;

/**
 * interface for a basic coordinate
 * @author William Kirby
 */
public interface ICoord {
    
    /**
     * retrieve the value on the y dimension
     * @return the value of y
     */
    public double getY();
    
    /**
     * change the value of y
     * @param y: value to set y
     */
    public void setY(double y);
    
    /**
     * retrieve the value on the x dimension
     * @return the value of x
     */
    public double getX();
    
    /**
     * change the value of x
     * @param x: value to set x
     */
    public void setX(double x);
}
