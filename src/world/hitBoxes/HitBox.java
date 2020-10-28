/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.hitBoxes;
import world.coords.ICoord;

/**
 * general hitBox interface
 * @author William Kirby
 */
public interface HitBox {
    
    /**
     * test if a given coordinate lies within the hitbox
     * @param coord: the coord to check
     * @return if the hitbox contains the point
     */
    public boolean containsCoord(ICoord coord);
    
    /**
     * is the hitbox touching this hitbox
     * @param hitBox: hitbox to test against
     * @return if touching
     */
    public boolean isTouching(HitBox hitBox);
}
