package com.endodog.timekeeping_management.model.base;

import com.endodog.timekeeping_management.constant.Message;
import com.endodog.timekeeping_management.constant.StatusCode;
import com.endodog.timekeeping_management.utils.DateFormat;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BaseResponse<T> {

  public static <T> ResponseEntity handlerResponsePage(Page<T> page) {
    Map<String, Object> data = new LinkedHashMap<>();
    List<T> content = page.getContent();
    if (content.isEmpty()) {
      data.put("status", StatusCode.OK.getStatusCode());
      data.put("timestamp", DateFormat.fDateVN());
      data.put("message", Message.NO_CONTENT.getMessage());
      data.put("data", null);
    } else {
      data.put("status", StatusCode.OK.getStatusCode());
      data.put("timestamp", DateFormat.fDateVN());
      data.put("message", Message.SUCCESS.getMessage());
      data.put("data", content);
      data.put("currentPage", page.getPageable().getPageNumber() + 1);
      data.put("totalPages", page.getTotalPages());
    }
    return new ResponseEntity<>(data, HttpStatus.valueOf((Integer) data.get("status")));
  }

  public static <T> ResponseEntity handlerResponseObject(T obj) {
    Map<String, Object> data = new LinkedHashMap<>();
    if (obj == null) {
      data.put("status", StatusCode.OK.getStatusCode());
      data.put("timestamp", DateFormat.fDateVN());
      data.put("message", Message.NO_CONTENT.getMessage());
      data.put("data", null);
    } else {
      data.put("status", StatusCode.OK.getStatusCode());
      data.put("timestamp", DateFormat.fDateVN());
      data.put("message", Message.SUCCESS.getMessage());
      data.put("data", obj);
    }
    return new ResponseEntity<>(data, HttpStatus.valueOf((Integer) data.get("status")));
  }

  public static ResponseEntity handlerResponseCustom(StatusCode statusCode, Message message) {
    Map<String, Object> data = new LinkedHashMap<>();
    data.put("status", statusCode.getStatusCode());
    data.put("timestamp", DateFormat.fDateVN());
    data.put("message", message.getMessage());
    return new ResponseEntity<>(data, HttpStatus.valueOf((Integer) data.get("status")));
  }

}
