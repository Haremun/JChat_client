package com.bieganski.jchat_client.connection;

import com.bieganski.jchat_client.utils.WebAddress;

import java.net.Socket;

public abstract class Connection {

    public abstract void connect(WebAddress webAddress);

    protected abstract void onConnected(Socket socket);

    protected abstract void onConnectionError(String message);

    protected abstract void onReceivedMessage(String message);
}
