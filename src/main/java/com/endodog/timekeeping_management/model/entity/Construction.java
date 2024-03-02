package com.endodog.timekeeping_management.model.entity;

import com.endodog.timekeeping_management.constant.ConstantSystem;
import com.endodog.timekeeping_management.constant.StatusConstruction;
import com.endodog.timekeeping_management.model.entity.base.BaseEntityAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
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
import java.util.Set;

@Entity
@Table(name = "construction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Construction extends BaseEntityAudit {

  @Column(updatable = false, length = ConstantSystem.CODE_LENGTH)
  private String code;

  private String name;

  private BigDecimal totalMoney;

  private BigDecimal amountReceived;

  @Enumerated(EnumType.STRING)
  private StatusConstruction status;

  @JsonIgnore
  @OneToMany(mappedBy = "construction")
  private Set<Attendance> attendances;

  @OneToMany(mappedBy = "construction", cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE}, orphanRemoval = true)
  private Set<Cost> costs;

}
