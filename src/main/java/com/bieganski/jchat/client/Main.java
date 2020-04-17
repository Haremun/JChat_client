package com.bieganski.jchat.client;

import com.bieganski.jchat.client.connection.Connection;
import com.bieganski.jchat.client.connection.ConnectionManager;
import com.bieganski.jchat.client.ui.ConsoleUi;
import com.bieganski.jchat.client.ui.Ui;
import com.bieganski.jchat.client.utils.SessionProperties;
import com.bieganski.jchat.client.utils.WebAddress;
import java.io.IOException;

/**
 * Chat application with tcp connection.
 *
 * @author Kamil Biegański
 */
public class Main {
  /**
   * Main function of program. Starts connection to server.
   *
   * @param args - no use of params now
   */
  public static void main(String[] args) throws IOException {
    SessionProperties sessionProperties = new SessionProperties(args[0]);

    Connection connectionManager = new ConnectionManager();
    Ui userUi = new ConsoleUi(connectionManager, sessionProperties);
    connectionManager.setConnectionCallback(userUi);
    //connectionManager.connect(new WebAddress("10.30.0.214", 8010));
    connectionManager.connect(new WebAddress("localhost", 8090));
  }
}
