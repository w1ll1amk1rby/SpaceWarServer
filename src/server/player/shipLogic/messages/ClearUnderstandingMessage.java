/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.player.shipLogic.messages;

import server.messaging.SendableMessage;

/**
 * 
 * @author William Kirby
 */
public class ClearUnderstandingMessage implements SendableMessage {
    
    private final static String PREFIX = "CLEAR_UNDERSTANDING";
    
    @Override
    public String messageToString() {
        return PREFIX;
    }
    
}
