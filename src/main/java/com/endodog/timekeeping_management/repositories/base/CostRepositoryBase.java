package com.endodog.timekeeping_management.repositories.base;

import com.endodog.timekeeping_management.model.dto.CostDTO;
import com.endodog.timekeeping_management.model.entity.Cost;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostRepositoryBase extends BaseRepository<Cost> {

  List<CostDTO> findCostsByConstructionId(String id);

}