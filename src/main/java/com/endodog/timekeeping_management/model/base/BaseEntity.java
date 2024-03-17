package com.endodog.timekeeping_management.model.base;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

  @Id
  @UuidGenerator
  private String id;

}
