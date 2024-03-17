package com.endodog.timekeeping_management.model.dto;

import com.endodog.timekeeping_management.constant.StatusConstruction;
import com.endodog.timekeeping_management.model.base.BaseDTO;
import com.endodog.timekeeping_management.model.projection.AttendanceProjection;
import com.endodog.timekeeping_management.model.projection.ConstructionDetailsProjection;
import com.endodog.timekeeping_management.model.projection.CostProjection;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ConstructionDetailsDTO extends BaseDTO {

  private String code;

  private String name;

  private BigDecimal totalMoney;

  private BigDecimal amountReceived;

  private StatusConstruction status;

  private Date createdAt;

  private List<CostProjection> cost;

  private List<AttendanceProjection> attendances;

  public ConstructionDetailsDTO(ConstructionDetailsProjection con, List<CostProjection> costs) {
    super(con.getId());
    this.code = con.getCode();
    this.name = con.getName();
    this.totalMoney = con.getTotalMoney();
    this.amountReceived = con.getAmountReceived();
    this.status = con.getStatus();
    this.createdAt = con.getCreatedAt();
    this.cost = costs;
  }

}
