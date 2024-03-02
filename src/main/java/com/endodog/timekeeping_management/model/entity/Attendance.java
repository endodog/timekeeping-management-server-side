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

@Entity
@Table(name = "attendance")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendance extends BaseEntityAudit {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "construction_id", referencedColumnName = "id")
  private Construction construction;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id", referencedColumnName = "id")
  private Employee employee;

}
