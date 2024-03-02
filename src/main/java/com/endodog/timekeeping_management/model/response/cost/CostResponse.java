package com.endodog.timekeeping_management.model.response.cost;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CostResponse {

  private String id;

  private BigDecimal total;

  private String costType;

  private Date createdAt;

}

