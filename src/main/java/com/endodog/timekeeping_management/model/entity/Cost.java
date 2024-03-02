package com.endodog.timekeeping_management.model.entity;

import com.endodog.timekeeping_management.model.entity.base.BaseEntityAudit;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "cost")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cost extends BaseEntityAudit {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "construction_id", referencedColumnName = "id")
  private Construction construction;

  private BigDecimal total;

  private String costType;

}
