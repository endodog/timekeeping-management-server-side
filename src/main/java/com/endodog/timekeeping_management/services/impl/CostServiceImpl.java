package com.endodog.timekeeping_management.services.impl;

import com.endodog.timekeeping_management.converter.impl.CostConverter;
import com.endodog.timekeeping_management.model.dto.CostDTO;
import com.endodog.timekeeping_management.model.entity.Cost;
import com.endodog.timekeeping_management.repositories.impl.CostRepositoryImpl;
import com.endodog.timekeeping_management.services.CostService;
import org.springframework.stereotype.Service;

@Service
public class CostServiceImpl extends AbstractServiceImpl<Cost, CostDTO, String> implements CostService {

  public CostServiceImpl(CostRepositoryImpl repository, CostConverter converter) {
    super(repository, converter);
  }

}
