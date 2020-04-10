package com.bieganski.jchat.client.connection;

import com.bieganski.jchat.client.ui.Color;
import com.bieganski.jchat.client.ui.Ui;
import com.bieganski.jchat.client.utils.UserProperties;
import com.bieganski.jchat.client.utils.WebAddress;

import java.io.IOException;
import java.net.Socket;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionManager extends Connection {

  private Ui userUi;
  private TcpSender tcpSender;
  private TcpListener tcpListener;

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

      JsonMsgWriter msgWriter = new JsonMsgWriter(socket);
      tcpSender = new TcpSender(msgWriter, userUi);
      new Thread(tcpSender,
          "Sender thread").start();

      JsonMsgListener msgListener = new JsonMsgListener(socket.getInputStream());
      tcpListener = new TcpListener(this, msgListener);
      new Thread(tcpListener,
          "Listener thread").start();

      msgWriter.writeMessage(
          new Message.MessageBuilder()
              .messageType(1)
              .author(UserProperties.USER)
              .build());

    } catch (IOException e) {
      userUi.printMessage("Connection error");
      log.error(e.getMessage());
    }
  }

  @Override
  protected void onConnectionError(String message) {
    userUi.printMessage("Server error: " + message);
    tcpListener.close();
    tcpSender.close();
    log.error(message);
  }

  @Override
  protected void onReceivedMessage(String message) {
    userUi.printMessage(message, Color.GREEN);
    log.debug("Received message: " + message);
  }
}
