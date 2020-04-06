package com.bieganski.jchat_client.connection;

import com.bieganski.jchat_client.utils.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

public class MessageWriter {
    private DataOutputStream outputStream;
    private ObjectMapper objectMapper = new ObjectMapper();

    public MessageWriter(Socket socket) throws IOException {
        this.outputStream = new DataOutputStream(socket.getOutputStream());
    }

    void writeMessage(String msg) throws IOException {
        Message message = new Message.MessageBuilder()
                .messageType(0)
                .author("Kamil")
                .date(LocalDateTime.now().toString())
                .message(msg)
                .build();
        outputStream.writeBytes(objectMapper.writeValueAsString(message) + "\n");
    }

    void writeMessage(Message msg) throws IOException {
        outputStream.writeBytes(objectMapper.writeValueAsString(msg) + "\n");
    }
}
