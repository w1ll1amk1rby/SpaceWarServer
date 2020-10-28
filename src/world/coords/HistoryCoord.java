package world.coords;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * the history coord remembers its last location 
 * @author William Kirby
 */
public class HistoryCoord implements ICoord {

    private double y;
    private double x;
    private final Coord previousCoord;
    
    /**
     * basic constructor for historycoord
     * @param x: the x of the coord
     * @param y: the y of the coord
     */
    public HistoryCoord(double x,double y) {
        this.x = x;
        this.y = y;
        this.previousCoord = new Coord(this.x,this.y);
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
        this.previousCoord.setX(this.x);
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
        this.previousCoord.setY(this.y);
        this.y = y;
    }
    
    /**
     * get the last position of this coord in the form of a different coord
     * this does update if this parent coord is updated
     * @return the last x and y values in the form of a coord
     */
    public ICoord getLastPosition() {
        return this.previousCoord;
    }
    
    /**
     * reset the history of the coord so that it 
     * @param x: the value of x for past coord
     * @param y: the value of y for past coord
     */
    public void resetBothCoords(double x,double y) {
        this.x = x;
        this.y = y;
        this.previousCoord.x = x;
        this.previousCoord.y = y;
    }
    
}
