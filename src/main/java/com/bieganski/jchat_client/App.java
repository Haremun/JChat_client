package com.bieganski.jchat_client;

import com.bieganski.jchat_client.connection.Connection;
import com.bieganski.jchat_client.connection.ConnectionManager;
import com.bieganski.jchat_client.ui.ConsoleUi;
import com.bieganski.jchat_client.ui.Ui;
import com.bieganski.jchat_client.utils.WebAddress;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        Ui userUi = new ConsoleUi(System.out, System.in);
        Connection connectionManager = new ConnectionManager(userUi);
        connectionManager.connect(new WebAddress("localhost", 8090));
        while (true){
            try {
                connectionManager.sendMessage(userUi.getUserInput());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
