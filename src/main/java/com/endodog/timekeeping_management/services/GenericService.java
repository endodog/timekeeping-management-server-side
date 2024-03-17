package com.endodog.timekeeping_management.services;

import com.endodog.timekeeping_management.constant.Sort;
import com.endodog.timekeeping_management.model.base.BaseDTO;
import com.endodog.timekeeping_management.model.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericService<T extends BaseEntity, P extends BaseDTO, ID extends Serializable> {

  Page<P> findAll(Pageable pageable, Object example, Sort sort);

  List<P> findAll(Pageable pageable, Sort sort);

  Optional<P> findById(ID id);

  long count(Object example);

  void save(P dto);

  void saveAll(List<P> dtos);

  P update(P dto);

  void deleteByIds(List<ID> ids);

  void deleteById(ID id);

  void deleteAll();

}
