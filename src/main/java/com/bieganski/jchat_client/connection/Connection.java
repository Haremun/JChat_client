package com.bieganski.jchat_client.connection;

import com.bieganski.jchat_client.utils.WebAddress;

import java.io.IOException;
import java.net.Socket;

public abstract class Connection {

    public abstract void connect(WebAddress webAddress);

    public abstract void sendMessage(String message) throws IOException;

    protected abstract void onConnected(Socket socket);

    protected abstract void onConnectionError(String message);

    protected abstract void onReceivedMessage(String message);
}
