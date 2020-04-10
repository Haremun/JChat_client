package com.bieganski.jchat.client.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleUi implements Ui {
  private final CommandProcessor commandProcessor = new CommandProcessor();
  private final BashBrush bashBrush = new BashBrush();
  private final Scanner scanner;
  private final PrintStream out;

  public ConsoleUi(PrintStream out, InputStream in) {
    this.scanner = new Scanner(in);
    this.out = out;
  }

  @Override
  public void printMessage(Object message) {
    out.println(message);
  }

  @Override
  public void printMessage(Object message, Color color) {
    out.println(bashBrush.colorString(message, color));
  }

  /**
   * Waits for user input. If first symbol is '/' string is processed as command.
   * Prints message after processing command.
   *
   * @return user input or empty string if user entered command
   */
  @Override
  public String getUserInput() {
    String userInput = scanner.nextLine();
    if (userInput.charAt(0) == '/') {
      String result = commandProcessor.processCommand(userInput);
      if (!result.isEmpty()) {
        printMessage(result, Color.BLUE);
      }
      return "";
    } else {
      return userInput;
    }
  }

  @Override
  public void notifyAboutNewUser(String user) {
    commandProcessor.addUser(user);
  }
}
