package com.bieganski.jchat.client.connection;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import com.bieganski.jchat.client.utils.WebAddress;

class MockServer implements Closeable {
  static final int ANY_PORT = 0;
  private ServerSocket serverSocket;
  private final int port;

  MockServer(int port) {
    this.port = port;
  }

  void startServer() throws IOException {
    serverSocket = new ServerSocket(port);
  }

  WebAddress getWebAddress() {
    return new WebAddress(serverSocket.getInetAddress().getHostAddress(), serverSocket.getLocalPort());
  }

  @Override
  public void close() throws IOException {
    serverSocket.close();
  }
}
