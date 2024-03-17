package com.endodog.timekeeping_management.repositories;

import com.endodog.timekeeping_management.model.dto.ConstructionListDTO;
import com.endodog.timekeeping_management.model.entity.Construction;

public interface ConstructionPaginationRepository
        extends GenericRepository<Construction, ConstructionListDTO, String> {

}