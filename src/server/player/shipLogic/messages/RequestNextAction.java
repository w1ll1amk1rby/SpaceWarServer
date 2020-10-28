/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.player.shipLogic.messages;

import server.messaging.SendableMessage;

/**
 * request the next action from the player
 * @author William Kirby
 */
public class RequestNextAction implements SendableMessage {

    private final static String PREFIX = "REQUEST_ACTION";
    
    @Override
    public String messageToString() {
        return PREFIX;
    }
    
    
}
