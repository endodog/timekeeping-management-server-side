package com.endodog.timekeeping_management.model.entity;

import com.endodog.timekeeping_management.constant.SystemConstant;
import com.endodog.timekeeping_management.constant.StatusConstruction;
import com.endodog.timekeeping_management.model.base.BaseEntityAudit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "construction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Construction extends BaseEntityAudit {

  @Column(updatable = false, length = SystemConstant.CODE_LENGTH)
  private String code;

  private String name;

  private BigDecimal totalMoney;

  private BigDecimal amountReceived;

  @Enumerated(EnumType.STRING)
  private StatusConstruction status;

  @OneToMany(mappedBy = "construction")
  private List<Attendance> attendances;

  @OneToMany(mappedBy = "construction")
  private List<Cost> costs;

  public void addCosts(List<Cost> costs) {
    this.costs.clear();
    this.costs.addAll(costs);
  }

  public void addAttendance(List<Attendance> attendances) {
    this.attendances.clear();
    this.attendances.addAll(attendances);
  }

}
