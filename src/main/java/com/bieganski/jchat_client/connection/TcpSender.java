package com.bieganski.jchat_client.connection;

import com.bieganski.jchat_client.ui.Ui;

import java.io.IOException;

public class TcpSender implements Runnable {

    private final MessageWriter messageWriter;
    private final Ui userUi;

    public TcpSender(MessageWriter messageWriter, Ui userUi) {
        this.messageWriter = messageWriter;
        this.userUi = userUi;
    }

    @Override
    public void run() {
        while (!messageWriter.isShutDown()) {
            try {
                messageWriter.writeMessage(userUi.getUserInput());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
