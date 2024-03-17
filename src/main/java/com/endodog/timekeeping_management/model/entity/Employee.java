package com.endodog.timekeeping_management.model.entity;

import com.endodog.timekeeping_management.constant.SystemConstant;
import com.endodog.timekeeping_management.constant.Role;
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
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends BaseEntityAudit {

  @Column(updatable = false, length = SystemConstant.CODE_LENGTH)
  private String code;

  private String fullName;

  @Column(unique = true)
  private String phoneNumber;

  private String password;

  private String avatar;

  private Integer attendance;

  private BigDecimal salary;

  private BigDecimal pendingSalary;

  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(mappedBy = "employee")
  private List<Attendance> attendances;

}
