package com.bieganski.jchat.client.connection;

import java.io.IOException;

public interface MessageListener {
  Message listenForMessage() throws IOException;
}
