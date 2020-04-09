package com.bieganski.jchat.client.ui;

public class BashBrush {
  private static final String RESET = "\033[0m";

  String colorString(Object stringToColor, Color color) {
    return String.format("%s%s%s", color.getColorHeader(), stringToColor, RESET);
  }
}
