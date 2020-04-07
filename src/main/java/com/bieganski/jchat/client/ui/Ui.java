package com.bieganski.jchat.client.ui;

/**
 * User interface to communicate with an application.
 */
public interface Ui {

  /**
   * Prints message on user interface.
   *
   * @param message - message to print
   */
  void printMessage(Object message);

  /**
   * Waits for user input.
   *
   * @return String with user input
   */
  String getUserInput();
}
