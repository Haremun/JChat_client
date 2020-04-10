package com.bieganski.jchat.client.connection;

import java.io.Closeable;
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
        connectionCallback.onReceivedMessage(message.getMessage());
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  void close() {
    running = false;
  }
}
