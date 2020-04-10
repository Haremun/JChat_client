package com.bieganski.jchat.client.connection;

import com.bieganski.jchat.client.ui.Color;
import com.bieganski.jchat.client.ui.Ui;

class MessageProcessor {
  private final Ui userInterface;

  public MessageProcessor(Ui userInterface) {
    this.userInterface = userInterface;
  }

  void process(Message message){
    switch (message.getMessageType()){
      case 0:
        userInterface.printMessage(
            String.format("%s: %s", message.getAuthor(), message.getMessage()),
            Color.GREEN);
        break;
      case 1:
        userInterface.printMessage(
            String.format("%s has connected to chat", message.getAuthor()),
            Color.BLUE);
        userInterface.notifyAboutNewUser(message.getAuthor());
        break;
    }
  }
}
