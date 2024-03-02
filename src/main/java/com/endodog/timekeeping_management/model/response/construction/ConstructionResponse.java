package com.endodog.timekeeping_management.model.response.construction;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ConstructionResponse {

  private String id;

  private String code;

  private String name;

  private BigDecimal totalMoney;

  private BigDecimal amountReceived;

}

