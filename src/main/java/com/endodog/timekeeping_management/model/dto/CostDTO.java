package com.endodog.timekeeping_management.model.dto;

import com.endodog.timekeeping_management.model.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CostDTO extends BaseDTO {

  private BigDecimal total;

  private String costType;

  private Date createdAt;

}

