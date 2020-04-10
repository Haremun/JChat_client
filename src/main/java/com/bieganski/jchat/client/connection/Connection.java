package com.bieganski.jchat.client.connection;

import com.bieganski.jchat.client.utils.WebAddress;
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

  protected abstract void onConnected(Socket socket);

  protected abstract void onConnectionError(String message);

  protected abstract void onReceivedMessage(Message message);
}
