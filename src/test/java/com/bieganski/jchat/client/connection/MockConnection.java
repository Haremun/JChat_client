package com.bieganski.jchat.client.connection;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import com.bieganski.jchat.client.utils.ConnectionCallback;
import com.bieganski.jchat.client.utils.Message;
import com.bieganski.jchat.client.utils.WebAddress;

class MockConnection extends Connection implements Closeable {
  private Socket socket;
  private String connectionError;
  private Message receivedMessage;

  boolean isConnected() {
    if (socket != null)
      return socket.isConnected();
    else
      return false;
  }

  Message getReceivedMessage() {
    return receivedMessage;
  }

  String getErrorMessage() {
    return connectionError;
  }

  @Override
  protected void onConnected(Socket socket) {
    this.socket = socket;
  }

  @Override
  protected void onConnectionError(String message) {
    connectionError = message;
  }

  @Override
  protected void onReceivedMessage(Message message) {
    receivedMessage = message;
  }

  @Override
  public void close() throws IOException {
    if (socket != null) {
      socket.close();
    }
  }

  //-------------------------------to remove
  @Override
  public void connect(WebAddress webAddress) {

  }

  @Override
  public void sendMessage(Message message) throws IOException {

  }

  @Override
  public void setConnectionCallback(ConnectionCallback connectionCallback) {

  }
}
