package com.endodog.timekeeping_management.model.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseDTO {

  private String id;

  public BaseDTO(String id) {
    this.id = id;
  }

}
