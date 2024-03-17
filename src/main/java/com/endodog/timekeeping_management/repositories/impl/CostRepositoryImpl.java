package com.endodog.timekeeping_management.repositories.impl;

import com.endodog.timekeeping_management.model.dto.CostDTO;
import com.endodog.timekeeping_management.model.entity.Cost;
import com.endodog.timekeeping_management.repositories.CostRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CostRepositoryImpl
        extends AbstractRepositoryImpl<Cost, CostDTO, String>
        implements CostRepository {

}
