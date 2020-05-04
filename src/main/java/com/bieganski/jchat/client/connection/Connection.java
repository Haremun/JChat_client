package com.bieganski.jchat.client.connection;

import com.bieganski.jchat.client.utils.ConnectionCallback;
import com.bieganski.jchat.client.utils.Message;
import com.bieganski.jchat.client.utils.WebAddress;
import java.io.IOException;
import java.net.Socket;

/**
 * Provides connection to TCP server.
 */
public abstract class Connection {

  /**
   * Connects to server on given address and port.
   *
   * @param webAddress - contains server address and port
   */
  public abstract void connect(WebAddress webAddress);

  public abstract void sendMessage(Message message) throws IOException;

  public abstract void setConnectionCallback(ConnectionCallback connectionCallback);

  //TODO Protected moved to ConnectionCallback
  protected abstract void onConnected(Socket socket);

  protected abstract void onConnectionError(String message);

  protected abstract void onReceivedMessage(Message message);
}
