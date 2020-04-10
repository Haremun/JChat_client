package com.bieganski.jchat.client.connection;

import com.bieganski.jchat.client.utils.UserProperties;
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

  /**
   * {@inheritDoc}
   */
  @Override
  void writeMessage(String msg) throws IOException {
    if (!msg.isEmpty()) {
      Message message = new Message.MessageBuilder()
          .messageType(0)
          .author(UserProperties.USER)
          .receiver(UserProperties.RECEIVER)
          .date(LocalDateTime.now().toString())
          .message(msg)
          .build();
      log.debug("Message to send: " + message.toString());
      objectMapper.writeValue(outputStream, message);
    }
  }

  @Override
  void writeMessage(Message msg) throws IOException {
    objectMapper.writeValue(outputStream, msg);
  }
}
