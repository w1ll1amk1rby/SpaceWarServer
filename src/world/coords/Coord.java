/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.coords;

/**
 * basic coordinate class
 * @author William Kirby
 */
public class Coord implements ICoord {
    
    protected double x,y; // value of position along each dimension
    
    /**
     * basic constructor for a given coord
     * @param x: coordinate
     * @param y: coordinate
     */
    public Coord(double x,double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * retrieve the value on the x dimension
     * @return the value of x
     */
    @Override
    public double getX() {
        return this.x;
    }
    
    /**
     * change the value of x
     * @param x: value to set x
     */
    @Override
    public void setX(double x) {
        this.x = x;
    }
    
    /**
     * retrieve the value on the y dimension
     * @return the value of y
     */
    @Override
    public double getY() {
        return this.y;
    }
    
    /**
     * change the value of y
     * @param y: value to set y
     */
    @Override
    public void setY(double y) {
        this.y = y;
    }
}
