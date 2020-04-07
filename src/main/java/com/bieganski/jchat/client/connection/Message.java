package com.bieganski.jchat.client.connection;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
class Message {
  private int messageType;
  private String author;
  private String date;
  private String message;

  //TODO wiki side for messages
  /**
   * Check wiki for information about messages.
   * @see <a href="https://google.pl/">Wiki</a>
   */
  private Message() {
  }

  static class MessageBuilder {
    private int messageType;
    private String author;
    private String date;
    private String message;

    MessageBuilder messageType(int id) {
      this.messageType = id;
      return this;
    }

    MessageBuilder author(String author) {
      this.author = author;
      return this;
    }

    MessageBuilder date(String date) {
      this.date = date;
      return this;
    }

    MessageBuilder message(String message) {
      this.message = message;
      return this;
    }

    Message build() {
      Message messageObj = new Message();
      messageObj.messageType = this.messageType;
      messageObj.author = this.author;
      messageObj.date = this.date;
      messageObj.message = this.message;
      return messageObj;
    }


  }
}
