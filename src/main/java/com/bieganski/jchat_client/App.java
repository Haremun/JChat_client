package com.bieganski.jchat_client;

import com.bieganski.jchat_client.connection.Connection;
import com.bieganski.jchat_client.connection.ConnectionManager;

public class App {
    public static void main(String[] args) {
        Connection connectionManager = new ConnectionManager();
        connectionManager.run();
    }
}
