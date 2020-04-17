package com.bieganski.jchat.client.connection;

import com.bieganski.jchat.client.utils.Message;
import java.io.IOException;

public interface MessageListener {
  Message listenForMessage() throws IOException;
}
