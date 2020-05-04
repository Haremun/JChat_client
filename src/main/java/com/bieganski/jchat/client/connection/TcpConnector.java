package com.bieganski.jchat.client.connection;

import com.bieganski.jchat.client.utils.WebAddress;
import java.io.IOException;
import java.net.Socket;

class TcpConnector implements Runnable {

  private final Connection connectionCallback;
  private final WebAddress webAddress;

  //TODO ConnectionCallback instead of connection
  TcpConnector(WebAddress webAddress, Connection connectionCallback) {
    this.webAddress = webAddress;
    this.connectionCallback = connectionCallback;
  }

  @Override
  public void run() {
    try {
      Socket socket = new Socket(webAddress.getAddress(), webAddress.getPort());
      connectionCallback.onConnected(socket);
    } catch (IOException e) {
      connectionCallback.onConnectionError(e.getMessage());
    }
  }
}
