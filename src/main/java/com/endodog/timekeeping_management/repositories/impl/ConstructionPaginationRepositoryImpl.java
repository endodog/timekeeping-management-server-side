package com.endodog.timekeeping_management.repositories.impl;

import com.endodog.timekeeping_management.model.dto.ConstructionListDTO;
import com.endodog.timekeeping_management.model.entity.Construction;
import com.endodog.timekeeping_management.repositories.ConstructionPaginationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ConstructionPaginationRepositoryImpl
        extends AbstractRepositoryImpl<Construction, ConstructionListDTO, String>
        implements ConstructionPaginationRepository {

}
