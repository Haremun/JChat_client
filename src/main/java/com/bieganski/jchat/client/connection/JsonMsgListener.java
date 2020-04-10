package com.bieganski.jchat.client.connection;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

class JsonMsgListener implements MessageListener {

  private final ObjectMapper objectMapper;
  private final InputStream inputStream;

  JsonMsgListener(InputStream inputStream) {
    JsonFactory jsonFactory = new JsonFactory();
    jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
    jsonFactory.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
    objectMapper = new ObjectMapper(jsonFactory);

    this.inputStream = inputStream;
  }

  @Override
  public Message listenForMessage() throws IOException {
    return objectMapper.readValue(inputStream, Message.class);
  }
}
