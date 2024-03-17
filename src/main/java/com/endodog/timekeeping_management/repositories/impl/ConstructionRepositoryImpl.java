package com.endodog.timekeeping_management.repositories.impl;

import com.endodog.timekeeping_management.model.dto.ConstructionDTO;
import com.endodog.timekeeping_management.model.entity.Construction;
import com.endodog.timekeeping_management.repositories.ConstructionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ConstructionRepositoryImpl
        extends AbstractRepositoryImpl<Construction, ConstructionDTO, String>
        implements ConstructionRepository {

}
