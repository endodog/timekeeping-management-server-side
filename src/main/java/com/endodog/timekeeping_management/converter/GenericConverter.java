package com.endodog.timekeeping_management.converter;

import com.endodog.timekeeping_management.model.base.BaseDTO;
import com.endodog.timekeeping_management.model.base.BaseEntity;

import java.util.List;

public interface GenericConverter<T extends BaseEntity, P extends BaseDTO> {

  T convertDtoToEntity(P dto);

  List<T> convertDtoListToEntityList(List<P> dtos);

  P convertEntityToDto(T entity);

  List<P> convertEntityListToDtoList(List<T> entities);

}
