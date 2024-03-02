package com.endodog.timekeeping_management.repository;

import com.endodog.timekeeping_management.model.entity.Construction;
import com.endodog.timekeeping_management.model.response.construction.ConstructionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstructionRepository extends JpaRepository<Construction, String> {
  @Query("""
          SELECT c.id as id, c.name as name, o.total as total, o.costType as costType
          FROM Construction c
          LEFT JOIN c.costs o on c.id = o.construction.id
          """)
  List<ConstructionResponse> getAll();
}