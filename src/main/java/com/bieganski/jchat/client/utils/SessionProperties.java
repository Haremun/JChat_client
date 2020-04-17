package com.bieganski.jchat.client.utils;

import lombok.Getter;

@Getter
public class SessionProperties {
  private final String user;
  private String receiver;

  public SessionProperties(String user) {
    this.user = user;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }
}
