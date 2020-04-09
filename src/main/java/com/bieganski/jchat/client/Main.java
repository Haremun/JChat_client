package com.bieganski.jchat.client;

import com.bieganski.jchat.client.connection.Connection;
import com.bieganski.jchat.client.connection.ConnectionManager;
import com.bieganski.jchat.client.ui.ConsoleUi;
import com.bieganski.jchat.client.ui.Ui;
import com.bieganski.jchat.client.utils.UserProperties;
import com.bieganski.jchat.client.utils.WebAddress;

/**
 * Chat application with tcp connection.
 * @author Kamil Biegański
 */
public class Main {
  /**
   * Main function of program. Starts connection to server.
   * @param args - no use of params now
   */
  public static void main(String[] args) {
    UserProperties.USER = args[0];

    Ui userUi = new ConsoleUi(System.out, System.in);
    Connection connectionManager = new ConnectionManager(userUi);
    connectionManager.connect(new WebAddress("localhost", 8090));

  }
}
