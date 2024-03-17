package com.endodog.timekeeping_management.services.impl;

import com.endodog.timekeeping_management.model.dto.ConstructionListDTO;
import com.endodog.timekeeping_management.model.entity.Construction;
import com.endodog.timekeeping_management.repositories.impl.ConstructionPaginationRepositoryImpl;
import com.endodog.timekeeping_management.services.ConstructionPaginationService;
import org.springframework.stereotype.Service;

@Service
public class ConstructionPaginationServiceImpl extends AbstractServiceImpl<Construction, ConstructionListDTO, String> implements ConstructionPaginationService {

  public ConstructionPaginationServiceImpl(ConstructionPaginationRepositoryImpl repository) {
    super(repository);
  }

}
