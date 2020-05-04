package com.bieganski.jchat.client.connection;

import com.bieganski.jchat.client.utils.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonMsgWriter extends MessageWriter {
  private final ObjectMapper objectMapper;

  //TODO Constructor with logic
  //Gets socket, only for super()
  JsonMsgWriter(Socket socket, ObjectMapper objectMapper) throws IOException {
    super(socket);
    this.objectMapper = objectMapper;
  }

  @Override
  void writeMessage(Message msg) throws IOException {
    objectMapper.writeValue(outputStream, msg);
    log.debug("Message to send: " + msg.toString());
  }
}
