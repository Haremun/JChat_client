package com.bieganski.jchat.client.connection;

import com.bieganski.jchat.client.utils.Message;
import com.bieganski.jchat.client.utils.SessionProperties;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonMsgWriter extends MessageWriter {
  private final ObjectMapper objectMapper;

  JsonMsgWriter(Socket socket) throws IOException {
    super(socket);
    JsonFactory jsonFactory = new JsonFactory();
    jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
    jsonFactory.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
    objectMapper = new ObjectMapper(jsonFactory);
  }

  @Override
  void writeMessage(Message msg) throws IOException {
    objectMapper.writeValue(outputStream, msg);
    log.debug("Message to send: " + msg.toString());
  }
}
