package com.bieganski.jchat.client.ui;

import com.bieganski.jchat.client.utils.Message;
import java.util.Arrays;
import java.util.List;

class MessageProcessor {
  private final Ui userInterface;
  private final List<String> usersCollection;

  MessageProcessor(List<String> usersCollection, Ui userInterface) {
    this.usersCollection = usersCollection;
    this.userInterface = userInterface;
  }

  void process(Message message) {
    switch (message.getMessageType()) {
      case 0:
        userInterface.printMessage(
            String.format("%s: %s", message.getAuthor(), message.getMessage()),
            Color.GREEN);
        break;
      case 1:
        usersCollection.add(message.getAuthor());
        userInterface.printMessage(
            String.format("%s has connected to chat", message.getAuthor()),
            Color.BLUE);
        break;
      case 2:
        usersCollection.addAll(Arrays.asList(message.getMessage().split(",")));
        break;
      case 3:
        usersCollection.remove(message.getMessage());
        break;
      default:
        break;
    }
  }
}
