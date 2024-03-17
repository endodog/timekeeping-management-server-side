package com.endodog.timekeeping_management.services.impl;

import com.endodog.timekeeping_management.converter.impl.ConstructionConverter;
import com.endodog.timekeeping_management.model.dto.ConstructionDTO;
import com.endodog.timekeeping_management.model.entity.Construction;
import com.endodog.timekeeping_management.repositories.impl.ConstructionRepositoryImpl;
import com.endodog.timekeeping_management.services.ConstructionService;
import org.springframework.stereotype.Service;

@Service
public class ConstructionServiceImpl extends AbstractServiceImpl<Construction, ConstructionDTO, String> implements ConstructionService {

  public ConstructionServiceImpl(ConstructionRepositoryImpl repository, ConstructionConverter converter) {
    super(repository, converter);
  }

}
