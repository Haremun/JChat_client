package com.bieganski.jchat.client.ui;

enum ConsoleCommands {
  PRIVATE("/priv"),
  JOIN("/join");

  private final String command;

  ConsoleCommands(String cmd) {
    this.command = cmd;
  }

  String getCommand() {
    return command;
  }
}
