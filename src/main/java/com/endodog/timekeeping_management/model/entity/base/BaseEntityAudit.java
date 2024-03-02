package com.endodog.timekeeping_management.model.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntityAudit extends BaseEntity {

  private String createdBy;

  private String updatedBy;

  private Boolean deleted = Boolean.FALSE;

  @CreationTimestamp
  private Date createdAt;

  @UpdateTimestamp
  @Column(updatable = false)
  private Date updatedAt;


}
