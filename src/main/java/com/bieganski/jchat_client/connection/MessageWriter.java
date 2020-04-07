package com.bieganski.jchat_client.connection;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

abstract class MessageWriter {
    protected OutputStream outputStream;
    private Socket socket;

    MessageWriter(Socket socket) throws IOException {
        this.outputStream = new DataOutputStream(socket.getOutputStream());
        this.socket = socket;
    }

    abstract void writeMessage(String msg) throws IOException;

    boolean isShutDown(){
        return socket.isOutputShutdown();
    }
}
