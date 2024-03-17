package com.endodog.timekeeping_management.repositories;

import com.endodog.timekeeping_management.constant.Sort;
import com.endodog.timekeeping_management.model.base.BaseDTO;
import com.endodog.timekeeping_management.model.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericRepository<T extends BaseEntity, P extends BaseDTO, ID extends Serializable> {

  Page<P> findAll(Pageable pageable, Object example, Sort sort);

  List<P> findAll(Pageable pageable, Sort sort);

  List<P> findAllByIds(List<ID> ids);

  Optional<P> findById(ID id);

  long count(Object example);

  void save(T t);

  void saveAll(List<T> entities);

  T update(T t);

  void deleteByIds(List<ID> ids);

  void deleteById(ID id);

  void deleteAll();

}
