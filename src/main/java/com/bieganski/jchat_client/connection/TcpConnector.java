package com.bieganski.jchat_client.connection;

import com.bieganski.jchat_client.utils.WebAddress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class TcpConnector implements Runnable {

    private Connection connectionCallback;
    private WebAddress webAddress;
    private boolean isConnected = false;

    TcpConnector(WebAddress webAddress, Connection connectionCallback) {
        this.webAddress = webAddress;
        this.connectionCallback = connectionCallback;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(webAddress.getAddress(), webAddress.getPort())) {
            isConnected = true;
            connectionCallback.onConnected(socket);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (isConnected) {
                connectionCallback.onReceivedMessage(reader.readLine());
            }
        } catch (IOException e) {
            connectionCallback.onConnectionError(e.getMessage());
        }
    }

    void closeConnection() {
        isConnected = false;
    }
}
