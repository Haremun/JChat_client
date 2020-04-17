package com.bieganski.jchat.client.utils;

public interface ConnectionCallback {
  void onReceivedMessage(Message message);

  void onConnected();

  void onConnectionError(String error);
}
