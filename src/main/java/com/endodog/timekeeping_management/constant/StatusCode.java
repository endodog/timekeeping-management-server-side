package com.endodog.timekeeping_management.constant;

public enum StatusCode {
  OK(200),
  NO_CONTENT(204),
  NOT_FOUND(404),
  BAD_REQUEST(400),
  SERVER_ERROR_COMMON(500);

  private int statusCode;

  StatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public int getStatusCode() {
    return statusCode;
  }

}
