package com.bieganski.jchat.client.connection;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import com.bieganski.jchat.client.utils.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JsonMsgListenerTest {
  @Test
  public void jsonMessageListenerShouldReturnExpectedValue() throws IOException {
    //Given
    Message expectedMessage = new Message.MessageBuilder().messageType(0).author("Test").message("Test message").build();
    InputStream inputStream = new ByteArrayInputStream(new ObjectMapper().writeValueAsBytes(expectedMessage));
    ObjectMapperFactory objectMapperFactory = new ObjectMapperFactory();
    JsonMsgListener jsonMsgListener = new JsonMsgListener(inputStream, objectMapperFactory.getUncloseObjectMapper());
    //When
    Message actualMessage = jsonMsgListener.listenForMessage();
    //Then
    Assert.assertEquals(actualMessage, expectedMessage);
  }
}
