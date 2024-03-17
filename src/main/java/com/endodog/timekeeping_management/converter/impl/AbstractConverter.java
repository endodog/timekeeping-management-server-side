package com.endodog.timekeeping_management.converter.impl;

import com.endodog.timekeeping_management.converter.GenericConverter;
import com.endodog.timekeeping_management.model.base.BaseDTO;
import com.endodog.timekeeping_management.model.base.BaseEntity;
import org.modelmapper.ModelMapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class AbstractConverter<T extends BaseEntity, P extends BaseDTO> implements GenericConverter<T, P> {

  private final Class<T> entityClass;
  private final Class<P> dtoClass;

  public AbstractConverter() {
    Type type = getClass().getGenericSuperclass();
    ParameterizedType parameterizedType = (ParameterizedType) type;
    entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    dtoClass = (Class<P>) parameterizedType.getActualTypeArguments()[1];
  }

  private ModelMapper getModelMapper() {
    return new ModelMapper();
  }

  @Override
  public T convertDtoToEntity(P dto) {
    return dto != null ? getModelMapper().map(dto, entityClass) : null;
  }

  @Override
  public List<T> convertDtoListToEntityList(List<P> dtos) {
    return dtos != null ? dtos.stream().map(r -> getModelMapper().map(r, entityClass)).collect(Collectors.toList()) : Collections.emptyList();
  }

  @Override
  public P convertEntityToDto(T entity) {
    return entity != null ? getModelMapper().map(entity, dtoClass) : null;
  }

  @Override
  public List<P> convertEntityListToDtoList(List<T> entities) {
    return entities != null ? entities.stream().map(e -> getModelMapper().map(e, dtoClass)).collect(Collectors.toList()) : Collections.emptyList();
  }


}
