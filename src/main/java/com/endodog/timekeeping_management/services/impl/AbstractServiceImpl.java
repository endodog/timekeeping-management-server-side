package com.endodog.timekeeping_management.services.impl;

import com.endodog.timekeeping_management.constant.Sort;
import com.endodog.timekeeping_management.converter.GenericConverter;
import com.endodog.timekeeping_management.model.base.BaseDTO;
import com.endodog.timekeeping_management.model.base.BaseEntity;
import com.endodog.timekeeping_management.repositories.GenericRepository;
import com.endodog.timekeeping_management.services.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class AbstractServiceImpl<T extends BaseEntity, P extends BaseDTO, ID extends Serializable> implements GenericService<T, P, ID> {

  private final GenericRepository<T, P, ID> repository;
  private final GenericConverter<T, P> converter;

  public AbstractServiceImpl(GenericRepository<T, P, ID> repository, GenericConverter<T, P> converter) {
    this.repository = repository;
    this.converter = converter;
  }

  public AbstractServiceImpl(GenericRepository<T, P, ID> repository) {
    this.repository = repository;
    this.converter = null;
  }

  @Override
  public Page<P> findAll(Pageable pageable, Object example, Sort sort) {
    return repository.findAll(pageable, example, sort);
  }

  @Override
  public List<P> findAll(Pageable pageable, Sort sort) {
    return repository.findAll(pageable, sort);
  }

  @Override
  public Optional<P> findById(ID id) {
    return repository.findById(id);
  }

  @Override
  public long count(Object example) {
    return repository.count(example);
  }

  @Override
  @Transactional
  public void save(P dto) {
    if (converter != null) {
      T entity = converter.convertDtoToEntity(dto);
      repository.save(entity);
    }
  }

  @Override
  @Transactional
  public void saveAll(List<P> dtos) {
    if (converter != null) {
      List<T> entities = converter.convertDtoListToEntityList(dtos);
      repository.saveAll(entities);
    }
  }

  @Override
  @Transactional
  public P update(P dto) {
    if (converter != null) {
      T entity = converter.convertDtoToEntity(dto);
      return converter.convertEntityToDto(repository.update(entity));
    }

    return null;
  }

  @Override
  @Transactional
  public void deleteByIds(List<ID> ids) {
    repository.deleteByIds(ids);
  }

  @Override
  @Transactional
  public void deleteById(ID id) {
    repository.deleteById(id);
  }

  @Override
  @Transactional
  public void deleteAll() {
    repository.deleteAll();
  }

}
