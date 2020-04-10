package com.bieganski.jchat.client.ui;

import com.bieganski.jchat.client.utils.UserProperties;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.bieganski.jchat.client.ui.ConsoleCommands.*;

public class CommandProcessor {
  private List<String> users = new LinkedList<>();

  String processCommand(String cmd) {
    String[] command = cmd.split(" ");

    //TODO it's temporary version, remove if
    if (command[0].equals(PRIVATE.getCommand())) {
      UserProperties.RECEIVER = command[1];
      return String.format("You are writing to %s", command[1]);
    }
    if (command[0].equals(LIST.getCommand())) {
      return String.join("\n", users);
    }
    return "There is no such command!";
  }

  void addUser(String user) {
    users.add(user);
  }
  void removeUser(String user){
    users.remove(user);
  }
}
