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
  private boolean running = true;

  public UserInputListener(Connection connection, Ui userUi) {
    this.connection = connection;
    this.userUi = userUi;
  }

  @Override
  public void run() {
    try {
      while (running) {
        connection.sendMessage(new Message.MessageBuilder()
            .messageType(0)
            .author(SessionProperties.USER)
            .receiver(SessionProperties.RECEIVER)
            .date(LocalDateTime.now().toString())
            .message(userUi.getUserInput())
            .build());
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
