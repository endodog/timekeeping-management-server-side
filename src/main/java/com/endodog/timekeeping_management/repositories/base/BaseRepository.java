package com.endodog.timekeeping_management.repositories.base;

import com.endodog.timekeeping_management.model.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Serializable> {

  // all method return DTO

  // method query support mapping allargs-constructor dto (not support relationship hbn)
  // not use noargs contructor(because use first) -> excep QueryTypeMismatchException
  // List<ConstructionDTO> findConstructionByNameContaining(String name);

  // name query not support mapping dto by contructor -> exception converter
  // -> use select new allargs-contructor from package
  // -> not support relationship hbn
//  @Query("""
//          SELECT new com.endodog.timekeeping_management.model.dto.ConstructionDTO(c.id, c.name, c.code)
//          FROM Construction c
//          INNER JOIN c.costs
//          WHERE c.name LIKE %:name%
//          """)
//  List<ConstructionDTO> findConstructionByNameContaining(@Param("name") String name);

// use projection interface -> support relationship hbn(must be itf)
// -> select all field
//  @Query("""
//          SELECT c
//          FROM Construction c
//          """)
//  List<ConstructionProjection> findConstructionByNameContaining(String name);

  //  @Query(value = """
//          SELECT c.id as id, c.name as name, c.totalMoney as totalMoney, c.status as status
//          FROM Construction c
//          WHERE (:#{#req.name} IS NULL OR c.name LIKE %:#{#req.name}%)
//          AND (:#{#req.status} IS NULL OR c.status = :#{#req.status})
//          """,
//          countQuery = """
//                  SELECT COUNT(c.id) FROM Construction c
//                  WHERE (:#{#req.name} IS NULL OR c.name LIKE %:#{#req.name}%)
//                  AND (:#{#req.status} IS NULL OR c.status = :#{#req.status})
//                          """)
//  Page<ConstructionDTO> findConstructionByNameAndStatus(Pageable pageable, Object example, Sort sort);

  //  @Query(value = """
//          SELECT c.id as id, c.code as code, c.name as name, c.totalMoney as totalMoney,
//          c.status as status, c.createdAt as createdAt, c.amountReceived as amountReceived
//          FROM Construction c
//          WHERE c.id = ?1
//          """)
//  Optional<> findByConstructionId(String id);

}
