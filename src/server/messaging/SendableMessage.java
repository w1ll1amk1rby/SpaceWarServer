/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.messaging;

/**
 * used to define a message that can be sent to the server
 * @author William Kirby
 */
public interface SendableMessage {
    
    /**
     * creates a string from the message that can be used to send data across
     * a network. if message is edited then the string is unchanged.
     * @return, message as a string
     */
    public abstract String messageToString();
}