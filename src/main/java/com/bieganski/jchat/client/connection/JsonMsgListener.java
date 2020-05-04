package com.bieganski.jchat.client.connection;

import com.bieganski.jchat.client.utils.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

class JsonMsgListener implements MessageListener {

  private final ObjectMapper objectMapper;
  private final InputStream inputStream;

  JsonMsgListener(InputStream inputStream, ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    this.inputStream = inputStream;
  }

  @Override
  public Message listenForMessage() throws IOException {
    return objectMapper.readValue(inputStream, Message.class);
  }
}
