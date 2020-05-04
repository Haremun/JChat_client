package com.bieganski.jchat.client.connection;

import com.bieganski.jchat.client.utils.Message;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

//TODO Interface instead of abstract class
abstract class MessageWriter {
  protected OutputStream outputStream;
  private final Socket socket;

  //TODO Remove new from constructor
  MessageWriter(Socket socket) throws IOException {
    this.outputStream = new DataOutputStream(socket.getOutputStream());
    this.socket = socket;
  }

  /**
   * Sends not empty string to server.
   *
   * @param msg - not null message to send.
   * @throws IOException when socked is closed
   */
  abstract void writeMessage(Message msg) throws IOException;

  boolean isShutDown() {
    return socket.isOutputShutdown();
  }
}
