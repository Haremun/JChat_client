package com.bieganski.jchat.client.ui;

import java.util.LinkedList;
import java.util.List;
import com.bieganski.jchat.client.utils.SessionProperties;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommandProcessorTest {
  @Test
  public void test() {
    //Given
    String user = "Kamil";
    String command = "/priv " + user;
    List<String> userList = new LinkedList<>();
    userList.add(user);
    CommandProcessor commandProcessor = new CommandProcessor(userList, new SessionProperties("Test"));
    //When
    String response = commandProcessor.processCommand(command);
    //Then
    Assert.assertEquals(response, String.format("You are writing to %s", user));
  }
}
