package com.endodog.timekeeping_management.model.response.base;

import com.endodog.timekeeping_management.constant.Message;
import com.endodog.timekeeping_management.constant.StatusCode;
import org.springframework.data.domain.Page;

import java.util.List;

public class ResponsePageBase<T> {

  private int code = StatusCode.OK.getStatusCode();
  private String message = Message.SUCCESS.getMessage();
  private List<T> data;
  private long totalPages;

  public ResponsePageBase(Page<T> page) {
    if (page != null) {
      this.data = page.getContent();
      this.totalPages = page.getTotalPages();
    }
  }

}
