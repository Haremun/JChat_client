package com.bieganski.jchat.client.connection;

import java.io.IOException;
import com.bieganski.jchat.client.utils.Message;

public class MockMessageListener implements MessageListener {

  static final Message EXCEPTION_MESSAGE =
      new Message.MessageBuilder().message("EXCEPTION").build();

  private final Message message;

  MockMessageListener(Message message) {
    this.message = message;
  }

  @Override
  public Message listenForMessage() throws IOException {
    if (message == EXCEPTION_MESSAGE) {
      throw new IOException("Message couldn't be received");
    }
    return message;
  }
}
