package com.bieganski.jchat.client.ui;

public enum Color {
  GREEN("\033[0;32m"),
  BLUE("\033[0;34m");

  private String colorHeader;

  Color(String colorHeader) {
    this.colorHeader = colorHeader;
  }

  String getColorHeader() {
    return colorHeader;
  }
}
