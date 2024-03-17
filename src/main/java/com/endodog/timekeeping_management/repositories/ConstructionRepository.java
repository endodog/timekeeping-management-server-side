package com.endodog.timekeeping_management.repositories;

import com.endodog.timekeeping_management.model.dto.ConstructionDTO;
import com.endodog.timekeeping_management.model.entity.Construction;

public interface ConstructionRepository
        extends GenericRepository<Construction, ConstructionDTO, String> {

}