package com.bieganski.jchat.client.ui;

import com.bieganski.jchat.client.connection.Connection;
import com.bieganski.jchat.client.utils.Message;
import com.bieganski.jchat.client.utils.SessionProperties;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserInputListener implements Runnable {

  private final Ui userUi;
  private final Connection connection;
  private final SessionProperties sessionProperties;
  private boolean running = true;

  UserInputListener(Connection connection, Ui userUi, SessionProperties sessionProperties) {
    this.connection = connection;
    this.userUi = userUi;
    this.sessionProperties = sessionProperties;
  }

  @Override
  public void run() {
    try {
      while (running) {
        String messageText = userUi.getUserInput();
        if (!messageText.isEmpty()) {
          connection.sendMessage(new Message.MessageBuilder()
              .messageType(0)
              .author(sessionProperties.getUser())
              .receiver(sessionProperties.getReceiver())
              .date(LocalDateTime.now().toString())
              .message(messageText)
              .build());
        }
      }
    } catch (IOException e) {
      userUi.printMessage("Error writing message");
      log.error(e.getMessage());
    }
  }

  void close() {
    running = false;
  }

}
