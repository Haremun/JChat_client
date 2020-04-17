package com.bieganski.jchat.client.ui;


import static com.bieganski.jchat.client.ui.ConsoleCommands.LIST;
import static com.bieganski.jchat.client.ui.ConsoleCommands.PRIVATE;

import com.bieganski.jchat.client.utils.SessionProperties;
import java.util.List;

class CommandProcessor {
  private final List<String> usersCollection;
  private final SessionProperties sessionProperties;

  CommandProcessor(List<String> usersCollection, SessionProperties sessionProperties) {
    this.sessionProperties = sessionProperties;
    this.usersCollection = usersCollection;
  }

  String processCommand(String cmd) {
    String[] command = cmd.split(" ");

    //TODO it's temporary version, remove if
    if (command[0].equals(PRIVATE.getCommand())) {
      sessionProperties.setReceiver(command[1]);
      return String.format("You are writing to %s", command[1]);
    } else if (command[0].equals(LIST.getCommand())) {
      return String.join("\n", usersCollection);
    }
    return "There is no such command!";
  }
}
