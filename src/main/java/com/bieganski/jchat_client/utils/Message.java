package com.bieganski.jchat_client.utils;

import lombok.Getter;

@Getter
public class Message {
    private int messageType;
    private String author;
    private String date;
    private String message;

    private Message() {
    }

    public static class MessageBuilder {
        private int messageType;
        private String author;
        private String date;
        private String message;

        public MessageBuilder messageType(int id) {
            this.messageType = id;
            return this;
        }

        public MessageBuilder author(String author) {
            this.author = author;
            return this;
        }

        public MessageBuilder date(String date) {
            this.date = date;
            return this;
        }

        public MessageBuilder message(String message) {
            this.message = message;
            return this;
        }

        public Message build() {
            Message messageObj = new Message();
            messageObj.messageType = this.messageType;
            messageObj.author = this.author;
            messageObj.date = this.date;
            messageObj.message = this.message;
            return messageObj;
        }


    }
}
