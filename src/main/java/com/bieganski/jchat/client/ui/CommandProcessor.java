package com.bieganski.jchat.client.ui;

import com.bieganski.jchat.client.utils.UserProperties;

import static com.bieganski.jchat.client.ui.ConsoleCommands.*;

public class CommandProcessor {
  String processCommand(String cmd) {
    String[] command = cmd.split(" ");

    //TODO it's temporary version, remove if
    if (command[0].equals(PRIVATE.getCommand())) {
      UserProperties.RECEIVER = command[1];
      return String.format("You are writing to %s", command[1]);
    }
    return "There is no such command!";
  }
}
