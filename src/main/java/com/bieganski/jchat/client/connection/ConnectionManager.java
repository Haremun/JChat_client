package com.bieganski.jchat.client.connection;

import com.bieganski.jchat.client.utils.ConnectionCallback;
import com.bieganski.jchat.client.utils.Message;
import com.bieganski.jchat.client.utils.WebAddress;
import java.io.IOException;
import java.net.Socket;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionManager extends Connection {

  //private final Ui userUi;
  private MessageWriter messageWriter;
  private TcpListener tcpListener;
  private ConnectionCallback connectionCallback;

  @Override
  public void connect(WebAddress serverAddress) {
    Thread connectionThread = new Thread(
        new TcpConnector(
            serverAddress, this),
        "Connection thread");
    connectionThread.start();
  }

  @Override
  public void sendMessage(Message message) throws IOException {
    messageWriter.writeMessage(message);
  }

  @Override
  public void setConnectionCallback(ConnectionCallback connectionCallback) {
    this.connectionCallback = connectionCallback;
  }

  @Override
  protected void onConnected(Socket socket) {
    try {
      messageWriter = new JsonMsgWriter(socket, new ObjectMapperFactory().getUncloseObjectMapper());

      JsonMsgListener msgListener;
      msgListener = new JsonMsgListener(socket.getInputStream(),
          new ObjectMapperFactory().getUncloseObjectMapper());

      tcpListener = new TcpListener(this, msgListener);
      new Thread(tcpListener,
          "Listener thread").start();

      connectionCallback.onConnected();
    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }

  @Override
  protected void onConnectionError(String message) {
    if (tcpListener != null) {
      tcpListener.close();
    }
    log.error(message);
  }

  @Override
  protected void onReceivedMessage(Message message) {
    if (connectionCallback != null) {
      connectionCallback.onReceivedMessage(message);
    }
    log.debug("Received message: " + message);
  }
}
