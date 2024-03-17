package com.endodog.timekeeping_management.model.dto;

import com.endodog.timekeeping_management.constant.StatusConstruction;
import com.endodog.timekeeping_management.model.base.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ConstructionDTO extends BaseDTO {

  private String code;

  private String name;

  private BigDecimal totalMoney;

  private BigDecimal amountReceived;

  private StatusConstruction status;

  private Date createdAt;

  private List<CostDTO> costs;

  public ConstructionDTO(String id, String code, String name, BigDecimal totalMoney, BigDecimal amountReceived, StatusConstruction status, Date createdAt) {
    super(id);
    this.code = code;
    this.name = name;
    this.totalMoney = totalMoney;
    this.amountReceived = amountReceived;
    this.status = status;
    this.createdAt = createdAt;
  }

}

