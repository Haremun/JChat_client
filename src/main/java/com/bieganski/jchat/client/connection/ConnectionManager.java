package com.bieganski.jchat.client.connection;

import com.bieganski.jchat.client.ui.Ui;
import com.bieganski.jchat.client.utils.WebAddress;
import java.io.IOException;
import java.net.Socket;

public class ConnectionManager extends Connection {

  private Ui userUi;
  private Thread tcpSender;

  public ConnectionManager(Ui userUi) {
    this.userUi = userUi;
  }

  @Override
  public void connect(WebAddress serverAddress) {
    Thread connectionThread = new Thread(
        new TcpConnector(
            serverAddress, this),
        "Connection thread");
    connectionThread.start();
  }

  @Override
  protected void onConnected(Socket socket) {
    try {
      userUi.printMessage("Connected to server");
      tcpSender = new Thread(new TcpSender(new JsonMsgWriter(socket), userUi),
          "Sender thread");
      tcpSender.start();
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
