package com.bieganski.jchat.client.utils;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Message {
  private int messageType;
  private String author;
  private String receiver;
  private String date;
  private String message;

  /**
   * Check wiki for information about messages.
   *
   * @see <a href="https://github.com/Haremun/JChat_client/wiki/Messages">Wiki</a>
   */
  private Message() {
  }

  public static class MessageBuilder {
    private int messageType;
    private String author;
    private String receiver;
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

    public MessageBuilder receiver(String receiver) {
      this.receiver = receiver;
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

    /**
     * Builds message.
     *
     * @return built message
     */
    public Message build() {
      Message messageObj = new Message();
      messageObj.messageType = this.messageType;
      messageObj.author = this.author;
      messageObj.receiver = this.receiver;
      messageObj.date = this.date;
      messageObj.message = this.message;
      return messageObj;
    }
  }
}
