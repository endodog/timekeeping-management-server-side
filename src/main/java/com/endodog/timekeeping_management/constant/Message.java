package com.endodog.timekeeping_management.constant;

public enum Message {
  SUCCESS("Success");

  private String message;

  Message(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
