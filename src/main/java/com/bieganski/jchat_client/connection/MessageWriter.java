package com.bieganski.jchat_client.connection;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MessageWriter {
    private DataOutputStream outputStream;

    public MessageWriter(Socket socket) throws IOException {
        this.outputStream = new DataOutputStream(socket.getOutputStream());
    }

    void writeMessage(String msg) throws IOException {
        outputStream.writeBytes(msg + "\n");
    }
}
