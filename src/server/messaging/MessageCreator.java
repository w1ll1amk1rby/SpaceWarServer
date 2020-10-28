/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.messaging;

/**
 * Used to convert strings to messages 
 * @author William Kirby
 */
public abstract class MessageCreator {
    
    /**
     * basic constructor no variables
     */
    public MessageCreator() {
        // doNothing
    }
    
    /**
     * converts the string to a message defined in the string
     * @param string: string to be converted
     * @return IncomingMessage: message that was created from the string or null
     */
    public abstract IncomingMessage convertStringToMessage(String string);
}