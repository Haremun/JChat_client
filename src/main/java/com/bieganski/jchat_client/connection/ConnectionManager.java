package com.bieganski.jchat_client.connection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnectionManager extends Connection {

    public void run() {
        Thread connectionThread = new Thread(
                new TcpConnector(
                        new WebAddress("localhost", 8090), this)
                , "Connection thread");
        connectionThread.start();
    }

    @Override
    protected void onConnected(Socket socket) {
        System.out.println("Connected");
        DataOutputStream outToServer = null;
        try {
            outToServer = new DataOutputStream(socket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outToServer.writeBytes("Yo!\n");
            System.out.println("Waiting for server response...");
            System.out.println(inFromServer.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onConnectionError(String message) {
        System.out.println(message);
    }
}
