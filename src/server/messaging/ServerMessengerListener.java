/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.messaging;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * listens for new incoming connections 
 * @author William Kirby
 */
public class ServerMessengerListener {
    
    private final int port; // port that the its listening on
    private ServerSocket serverSocket; // serversocket its listening on
    
    /**
     * basic constructor requires the port.
     * @param port: port to listen on.
     * @throws java.io.IOException if erverSaocket failed to set up
     */
    public ServerMessengerListener(int port) throws IOException {
        this.port = port;
        try {
            this.serverSocket = new ServerSocket(this.port);
        } 
        catch(IOException e) {
            throw e;
        }
    }
    
    /**
     * wait for the next connection to come in from a client
     * @return the messenger of the next connection
     * @throws IOException if while waiting socket runs in to an IOexception
     */
    public final Messenger waitForNextConnection() throws IOException {
        Socket socket = this.serverSocket.accept();
        return new Messenger(socket);
    }
    
    /**
     * get the port number it is listening on.
     * @return the port number
     */
    public final int getPort() {
        return this.port;
    }
    
    /**
     * returns if the socket is still listening
     * @return boolean value if the server is closed
     */
    public final boolean isClosed() {
        if(this.serverSocket == null) {
            return true;
        }
        return serverSocket.isClosed();
    }
    
    /**
     * close the socket thats its listening on
     */
    public final void close() {
        if(this.serverSocket != null) {
            try{
                this.serverSocket.close();
            }
            catch(IOException e) {
                // do nothing
            }
        }
    }
}
