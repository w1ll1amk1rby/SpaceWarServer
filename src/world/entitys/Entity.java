/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.entitys;

import world.coords.ICoord;

/**
 * entity with a given coordinate
 * @author William Kirby
 */
public abstract class Entity {
    
    protected final ICoord centre; // centre of the entity
    
    /**
     * basic constructor to make a basic Entity
     * @param centre: the centre of the Entity
     */
    protected Entity(ICoord centre) {
        this.centre = centre;
    }
    
    /**
     * retrieve the centre of the entity.
     * @return the centre
     */
    public ICoord getCentre() {
        return this.centre;
    }
}
