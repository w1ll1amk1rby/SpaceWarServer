/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.messaging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Basic messenger class that uses a socket to convert strings to messages as 
 * well as send messages to users
 * @author William Kirby
 */
public class Messenger {
    
    protected final Socket socket; // socket that the messanger is using
    protected final BufferedReader bufferedReader; // used to get messages through the socket
    protected final PrintWriter printWriter; //  used to send messages through the socket
    protected MessageCreator messageCreator; // message creator the messenger uses
    
    /**
     * Basic constructor for messenger, requires the socket to communicate with
     * @param socket: Socket, where the messenger is sending and receiving messages from
     * @throws java.io.IOException: if the socket is closed
     */
    protected Messenger(Socket socket) throws IOException {
        this.socket = socket;
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.printWriter = new PrintWriter(socket.getOutputStream()); 
        }
        catch(IOException exception) {  // catch if socket is already closed 
            this.close();
            throw exception;
        }
    }
    
    /**
     * returns the next message sent from user if there is none then returns null
     * @return, IncomingMessage: next message avaliable.
     * @throws java.io.IOException: if socket is closed.
     */
    public IncomingMessage getMessage() throws IOException {
        try {
            String message = this.bufferedReader.readLine();
            return this.messageCreator.convertStringToMessage(message);
        }
        catch(Exception e) {
            this.close();
            throw new IOException();
        }
    }
    
    /**
     * sends a single message through the socket.
     * @param message: message to send.
     * @throws IOException, if socket is closed.
     */
    public synchronized void addMessage(SendableMessage message) throws IOException {
        String stringMessage = message.messageToString();
        System.out.println(stringMessage);
        this.printWriter.println(stringMessage);
    }
    
    /** 
     * send a list of messages through the socket.
     * @param messages: list of messages to send
     * @throws IOException, if socket closed
     */
    public synchronized void addMessages(ArrayList<SendableMessage> messages) throws IOException {
        for(int i = 0;i < messages.size();i++) {
            this.addMessage(messages.get(i));
        }
    }
    
    /**
     * flush the messages out of the messenger
     */
    public synchronized void flushMessages() {
        this.printWriter.flush();
    }
    
    /**
     * replace the message creator.
     * @param messageCreator: the message creator the messenger will use, can be overwritten.
     */
    public final void setMessageCreator(MessageCreator messageCreator) {
        this.messageCreator = messageCreator;
    }
    
    /**
     * returns a boolean if the messenger is closed
     * @return boolean: is messenger closed
     */
    public final boolean isClosed() {
        return this.socket.isClosed();  
    }
    
    /**
     * close the messenger 
     */
    public final void close() {
        this.printWriter.close();
        try {
            this.bufferedReader.close();
        }
        catch(IOException exception) {
            //doNothing
        }
        try {
            this.socket.close();
        }
        catch(IOException exception) {
            //doNothing
        }
    }
}