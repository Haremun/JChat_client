package com.bieganski.jchat.client.connection;

import com.bieganski.jchat.client.ui.Ui;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TcpSender implements Runnable {

  private final MessageWriter messageWriter;
  private final Ui userUi;

  public TcpSender(MessageWriter messageWriter, Ui userUi) {
    this.messageWriter = messageWriter;
    this.userUi = userUi;
  }

  @Override
  public void run() {
    while (!messageWriter.isShutDown()) {
      try {
        messageWriter.writeMessage(userUi.getUserInput());
      } catch (IOException e) {
        userUi.printMessage("Error writing message");
        log.error(e.getMessage());
      }
    }
  }
}
