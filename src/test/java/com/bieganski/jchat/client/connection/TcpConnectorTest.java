package com.bieganski.jchat.client.connection;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import com.bieganski.jchat.client.utils.WebAddress;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TcpConnectorTest {
  private MockServer mockServer;

  @BeforeClass
  public void setUpServer() throws IOException {
    mockServer = new MockServer(MockServer.ANY_PORT);
    mockServer.startServer();
  }

  @Test(groups = "connection")
  public void connectionShouldBeEstablishedWithServerMock() throws IOException {
    //Given
    try (MockConnection mockConnection = new MockConnection()) {
      //When
      new TcpConnector(mockServer.getWebAddress(), mockConnection).run();
      //Then
      assertTrue(mockConnection.isConnected(), "Connection could not be established");
    }
  }

  @Test(groups = "connection")
  public void connectionShouldReturnErrorMessageWhenConnectionCannotBeEstablished() throws IOException {
    //Given
    try (MockConnection mockConnection = new MockConnection()) {
      //When
      new TcpConnector(new WebAddress("", 0), mockConnection).run();
      //Then
      assertNotNull(mockConnection.getErrorMessage());
    }
  }

  @AfterClass
  public void closeServer() throws IOException {
    mockServer.close();
  }
}
