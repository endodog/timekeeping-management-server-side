package com.endodog.timekeeping_management.model.dto;

import com.endodog.timekeeping_management.annotations.Equal;
import com.endodog.timekeeping_management.annotations.Like;
import com.endodog.timekeeping_management.constant.StatusConstruction;
import com.endodog.timekeeping_management.model.base.BasePageableRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ConstructionRequestParams extends BasePageableRequest {

  @Like
  private String name;

  @Equal
  private StatusConstruction status;

}
