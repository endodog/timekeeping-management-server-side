package com.endodog.timekeeping_management.repositories;

import com.endodog.timekeeping_management.model.dto.CostDTO;
import com.endodog.timekeeping_management.model.entity.Cost;

public interface CostRepository
        extends GenericRepository<Cost, CostDTO, String> {

}