package com.bieganski.jchat.client.connection;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

abstract class MessageWriter {
  protected OutputStream outputStream;
  private Socket socket;

  MessageWriter(Socket socket) throws IOException {
    this.outputStream = new DataOutputStream(socket.getOutputStream());
    this.socket = socket;
  }

  /**
   * Sends not empty string to server.
   * @param msg - message to send. Can't be empty or null.
   * @throws IOException
   */
  abstract void writeMessage(String msg) throws IOException;

  abstract void writeMessage(Message msg) throws IOException;

  boolean isShutDown() {
    return socket.isOutputShutdown();
  }
}
