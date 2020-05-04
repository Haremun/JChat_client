package com.bieganski.jchat.client.connection;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import com.bieganski.jchat.client.utils.Message;
import org.testng.annotations.Test;

/**
 * Thread.sleep() is used for waiting after while loop
 */
public class TcpListenerTest {

  @Test
  public void messageFromMsgListenerShouldBeEqualMessageInTcpListener() throws IOException, InterruptedException {
    //Given
    Message message = new Message.MessageBuilder().messageType(0).author("Test").message("Test message").build();
    try (MockConnection mockConnection = new MockConnection()) {
      TcpListener tcpListener = new TcpListener(mockConnection, new MockMessageListener(message));
      //When
      new Thread(tcpListener).start();
      Thread.sleep(10);
      //Then
      assertEquals(mockConnection.getReceivedMessage(), message);
      tcpListener.close();
    }
  }

  @Test
  public void tcpListenerShouldReturnErrorMessageAfterException() throws IOException, InterruptedException {
    //Given
    Message message = MockMessageListener.EXCEPTION_MESSAGE;
    try (MockConnection mockConnection = new MockConnection()) {
      TcpListener tcpListener = new TcpListener(mockConnection, new MockMessageListener(message));
      //When
      new Thread(tcpListener).start();
      Thread.sleep(10);
      //Then
      assertEquals(mockConnection.getErrorMessage(), "Connection lost");
      tcpListener.close();
    }
  }
}
