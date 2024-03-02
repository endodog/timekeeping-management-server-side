package com.endodog.timekeeping_management.model.response.base;

import com.endodog.timekeeping_management.constant.Message;
import com.endodog.timekeeping_management.constant.StatusCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBase<T> {

  private int code;
  private String message;
  private T data;

  public ResponseBase(T data) {
    if (data != null) {
      this.code = StatusCode.OK.getStatusCode();
      this.message = Message.SUCCESS.getMessage();
      this.data = data;
    }
  }

}
