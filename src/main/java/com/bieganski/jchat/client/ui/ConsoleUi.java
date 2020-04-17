package com.bieganski.jchat.client.ui;

import com.bieganski.jchat.client.connection.Connection;
import com.bieganski.jchat.client.utils.Message;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUi implements Ui {
  private final List<String> users = new LinkedList<>();
  private final CommandProcessor commandProcessor = new CommandProcessor(users);
  private final MessageProcessor messageProcessor = new MessageProcessor(users, this);
  private final BashBrush bashBrush = new BashBrush();
  private final Scanner scanner;
  private final PrintStream out = System.out;

  public ConsoleUi(Connection connection, InputStream in) {
    this.scanner = new Scanner(in);
    new Thread(new UserInputListener(connection, this)).start();
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
  public void onReceivedMessage(Message message) {
    messageProcessor.process(message);
  }

  @Override
  public void onConnectionError(String error) {

  }
}
