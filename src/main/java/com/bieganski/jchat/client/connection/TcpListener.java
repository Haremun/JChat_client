package com.bieganski.jchat.client.connection;

import com.bieganski.jchat.client.utils.Message;
import java.io.IOException;

class TcpListener implements Runnable {
  private final MessageListener messageListener;
  private final Connection connectionCallback;
  private boolean running = true;

  //TODO Inject ConnectionCallback, not Connection
  TcpListener(Connection connectionCallback, MessageListener messageListener) {
    this.connectionCallback = connectionCallback;
    this.messageListener = messageListener;
  }

  @Override
  public void run() {
    try {
      //TODO What use instead of while?
      while (running) {
        Message message = messageListener.listenForMessage();
        connectionCallback.onReceivedMessage(message);
      }
    } catch (IOException e) {
      //TODO Better message
      connectionCallback.onConnectionError("Connection lost");
    }
  }

  //TODO closeable?
  void close() {
    running = false;
  }
}
