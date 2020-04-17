package com.bieganski.jchat.client.utils;

public interface ConnectionCallback {
  void onReceivedMessage(Message message);

  void onConnectionError(String error);
}
