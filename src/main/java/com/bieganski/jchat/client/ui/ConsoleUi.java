package com.bieganski.jchat.client.ui;

import com.bieganski.jchat.client.connection.Connection;
import com.bieganski.jchat.client.utils.Message;
import com.bieganski.jchat.client.utils.SessionProperties;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class ConsoleUi implements Ui {
  private final List<String> users = new LinkedList<>();
  private final CommandProcessor commandProcessor;
  private final MessageProcessor messageProcessor = new MessageProcessor(users, this);
  private final BashBrush bashBrush = new BashBrush();
  private final Scanner scanner = new Scanner(System.in);
  private final PrintStream out = System.out;
  private final Connection connection;
  private final SessionProperties sessionProperties;

  /**
   * Initialize console ui and starts ui listener thread.
   *
   * @param connection        connection with server
   * @param sessionProperties properties of current session
   */
  public ConsoleUi(Connection connection, SessionProperties sessionProperties) {
    this.connection = connection;
    this.sessionProperties = sessionProperties;
    commandProcessor = new CommandProcessor(users, sessionProperties);
    new Thread(new UserInputListener(connection, this, sessionProperties)).start();
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
  public void onConnected() {
    try {
      printMessage("Connected to chat");
      connection.sendMessage(new Message.MessageBuilder()
          .messageType(1)
          .author(sessionProperties.getUser())
          .build());
    } catch (IOException e) {
      printMessage(e.getMessage());
    }
  }

  @Override
  public void onConnectionError(String error) {
    printMessage(error);
  }
}
