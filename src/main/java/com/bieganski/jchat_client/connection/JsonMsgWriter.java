package com.bieganski.jchat_client.connection;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

public class JsonMsgWriter extends MessageWriter {
    private ObjectMapper objectMapper;

    JsonMsgWriter(Socket socket) throws IOException {
        super(socket);
        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        jsonFactory.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
        objectMapper = new ObjectMapper(jsonFactory);
    }

    @Override
    void writeMessage(String msg) throws IOException {
        Message message = new Message.MessageBuilder()
                .messageType(0)
                .author("Kamil")
                .date(LocalDateTime.now().toString())
                .message(msg)
                .build();

        objectMapper.writeValue(outputStream, message);
    }
}