package com.bieganski.jchat.client.ui;

import com.bieganski.jchat.client.utils.ConnectionCallback;

/**
 * User interface to communicate with application.
 */
public interface Ui extends ConnectionCallback {

  /**
   * Prints message on user interface.
   *
   * @param message message to print
   */
  void printMessage(Object message);

  /**
   * Prints message on user interface with color.
   *
   * @param message message to print
   * @param color   message color
   */
  void printMessage(Object message, Color color);

  /**
   * Waits for user input.
   *
   * @return String with user input
   */
  String getUserInput();
}
