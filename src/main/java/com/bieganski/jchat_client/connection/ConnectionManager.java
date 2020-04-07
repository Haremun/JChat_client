package com.bieganski.jchat_client.connection;

import com.bieganski.jchat_client.ui.Ui;
import com.bieganski.jchat_client.utils.WebAddress;

import java.io.IOException;
import java.net.Socket;

public class ConnectionManager extends Connection {

    private Ui userUi;
    private MessageWriter messageWriter;

    public ConnectionManager(Ui userUi) {
        this.userUi = userUi;
    }

    public void connect(WebAddress serverAddress) {
        Thread connectionThread = new Thread(
                new TcpConnector(
                        serverAddress, this)
                , "Connection thread");
        connectionThread.start();
    }

    @Override
    public void sendMessage(String message) throws IOException {
        messageWriter.writeMessage(message);
    }

    @Override
    protected void onConnected(Socket socket) {
        try {
            userUi.printMessage("Connected to server");
            messageWriter = new JsonMsgWriter(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onConnectionError(String message) {
        userUi.printMessage("Server error: " + message);
    }

    @Override
    protected void onReceivedMessage(String message) {
        userUi.printMessage(message);
    }
}
