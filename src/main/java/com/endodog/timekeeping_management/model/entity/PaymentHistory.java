package com.endodog.timekeeping_management.model.entity;

import com.endodog.timekeeping_management.model.base.BaseEntityAudit;
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
@Table(name = "payment_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentHistory extends BaseEntityAudit {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id", referencedColumnName = "id")
  private Employee employee;

  private BigDecimal paymentAmount;

  private Boolean paymentType;

  private String evidence;

}
