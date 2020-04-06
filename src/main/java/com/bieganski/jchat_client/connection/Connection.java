package com.bieganski.jchat_client.connection;

import java.net.Socket;

public abstract class Connection {
    public abstract void run();

    protected abstract void onConnected(Socket socket);
    protected abstract void onConnectionError(String message);
}
