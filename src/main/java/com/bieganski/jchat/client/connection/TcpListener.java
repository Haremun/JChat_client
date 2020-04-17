package com.bieganski.jchat.client.connection;

import com.bieganski.jchat.client.utils.Message;
import java.io.IOException;

class TcpListener implements Runnable {
  private final MessageListener messageListener;
  private final Connection connectionCallback;
  private boolean running = true;

  TcpListener(Connection connectionCallback, MessageListener messageListener) {
    this.connectionCallback = connectionCallback;
    this.messageListener = messageListener;
  }

  @Override
  public void run() {
    try {
      while (running) {
        Message message = messageListener.listenForMessage();
        connectionCallback.onReceivedMessage(message);
      }
    } catch (IOException e) {
      connectionCallback.onConnectionError("Connection lost");
    }
  }

  void close() {
    running = false;
  }
}
