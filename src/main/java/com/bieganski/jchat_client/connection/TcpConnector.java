package com.bieganski.jchat_client.connection;

import java.io.IOException;
import java.net.Socket;

class TcpConnector implements Runnable {

    private Connection connectionCallback;
    private WebAddress webAddress;

    TcpConnector(WebAddress webAddress, Connection connectionCallback) {
        this.webAddress = webAddress;
        this.connectionCallback = connectionCallback;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(webAddress.getAddress(), webAddress.getPort())) {
            connectionCallback.onConnected(socket);
            while (true) {

            }
        } catch (IOException e) {
            connectionCallback.onConnectionError(e.getMessage());
        }
    }
}
