package com.endodog.timekeeping_management.model.dto;

import com.endodog.timekeeping_management.constant.StatusConstruction;
import com.endodog.timekeeping_management.model.base.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ConstructionListDTO extends BaseDTO {

  private String name;

  private BigDecimal totalMoney;

  private StatusConstruction status;

  private Date createdAt;

  public ConstructionListDTO(String id, String name, BigDecimal totalMoney, StatusConstruction status, Date createdAt) {
    super(id);
    this.name = name;
    this.totalMoney = totalMoney;
    this.status = status;
    this.createdAt = createdAt;
  }

}

