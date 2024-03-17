package com.endodog.timekeeping_management.model.dto;

import com.endodog.timekeeping_management.model.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO extends BaseDTO {

  private String employeeId;

}

