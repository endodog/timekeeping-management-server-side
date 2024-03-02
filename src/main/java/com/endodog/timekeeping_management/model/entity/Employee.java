package com.endodog.timekeeping_management.model.entity;

import com.endodog.timekeeping_management.constant.ConstantSystem;
import com.endodog.timekeeping_management.constant.Role;
import com.endodog.timekeeping_management.model.entity.base.BaseEntityAudit;
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
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends BaseEntityAudit {

  @Column(updatable = false, length = ConstantSystem.CODE_LENGTH)
  private String code;

  private String fullName;

  @Column(unique = true)
  private String phoneNumber;

  private String password;

  private String avatar;

  private String address;

  private Integer attendance;

  private BigDecimal salary;

  private BigDecimal pendingSalary;

  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(mappedBy = "employee")
  private Set<Attendance> attendances;

}
