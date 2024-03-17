package com.endodog.timekeeping_management.model.base;

import com.endodog.timekeeping_management.constant.SystemConstant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BasePageableRequest {

  private int currentPage = SystemConstant.CURRENT_PAGE_DEFAULT;
  private int pageSize = SystemConstant.PAGE_SIZE_DEFAULT;

}
