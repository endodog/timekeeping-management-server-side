package com.endodog.timekeeping_management.controller;

import com.endodog.timekeeping_management.constant.Message;
import com.endodog.timekeeping_management.constant.Sort;
import com.endodog.timekeeping_management.constant.StatusCode;
import com.endodog.timekeeping_management.constant.StatusConstruction;
import com.endodog.timekeeping_management.converter.impl.ConstructionConverter;
import com.endodog.timekeeping_management.model.base.BaseResponse;
import com.endodog.timekeeping_management.model.dto.ConstructionDTO;
import com.endodog.timekeeping_management.model.dto.ConstructionListDTO;
import com.endodog.timekeeping_management.model.dto.ConstructionRequestParams;
import com.endodog.timekeeping_management.repositories.base.ConstructionRepositoryBase;
import com.endodog.timekeeping_management.repositories.base.CostRepositoryBase;
import com.endodog.timekeeping_management.repositories.impl.ConstructionRepositoryImpl;
import com.endodog.timekeeping_management.services.ConstructionService;
import com.endodog.timekeeping_management.services.impl.ConstructionPaginationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.endodog.timekeeping_management.constant.ApiConstant.ConstructionApi.ROOT_API;

@RestController
@RequestMapping(ROOT_API)
public class ConstructionController {

  @Autowired
  private ConstructionService constructionService;

  @Autowired
  private ConstructionRepositoryBase repository;

//  @Autowired
//  private CostService costService;

  @Autowired
  private CostRepositoryBase costRepositoryBase;

  @Autowired
  private ConstructionConverter converter;

  @Autowired
  private ConstructionRepositoryImpl constructionRepositoryImpl;

  @Autowired
  private ConstructionPaginationServiceImpl constructionPaginationService;

  @GetMapping("/test")
  public ResponseEntity<ConstructionDTO> findAllConstructions(@RequestParam("id") String id) {
    ConstructionDTO save = new ConstructionDTO();
    save.setCode("codeSave");
    save.setTotalMoney(new BigDecimal(154123));
    save.setName("25bb");
    save.setStatus(StatusConstruction.COMPLETED);

    constructionService.save(save);
    return BaseResponse.handlerResponseCustom(StatusCode.OK, Message.SUCCESS);
  }

  @GetMapping("/test1")
  public ResponseEntity<Page<ConstructionDTO>> findllConstructions(ConstructionRequestParams req) {
    Pageable pageable = PageRequest.of(req.getCurrentPage() - 1, req.getPageSize());
    Page<ConstructionListDTO> page = constructionPaginationService.findAll(pageable, req, Sort.byDesc("createdAt"));
    return BaseResponse.handlerResponsePage(page);
  }

  @GetMapping("/test2")
  public ResponseEntity<List<ConstructionDTO>> findAlConstructions(@RequestParam("ids") List<String> ids) {
    List<ConstructionDTO> constructions = constructionRepositoryImpl.findAllByIds(ids);
    return BaseResponse.handlerResponseObject(constructions);
  }

  @GetMapping("/test3")
  public ResponseEntity<ConstructionDTO> findAllonstructions(@RequestParam("id") String id) {
    Optional<ConstructionDTO> construction = constructionRepositoryImpl.findById(id);
    return BaseResponse.handlerResponseObject(construction);
  }

//  @GetMapping
//  public ResponseEntity<Page<ConstructionListProjection>> findAllConstructions(
//          ConstructionRequestParams req) {
//    Page<ConstructionListProjection> constructions = constructionService.findConstructionByNameAndStatus(req);
//    return BaseResponse.handlerResponsePage(constructions);
//  }
//
//  @GetMapping("/{id}")
//  public ResponseEntity<ConstructionDetailsDTO> findConstruction(
//          @PathVariable("id") String id) {
//    ConstructionDetailsProjection construction = constructionService.findByConstructionId(id);
//    List<CostProjection> costs = costService.findCostsByConstructionId(id);
//    ConstructionDetailsDTO combinedDetails = new ConstructionDetailsDTO(construction, costs);
//    return BaseResponse.handlerResponseObject(combinedDetails);
//  }

  @PostMapping
  public ResponseEntity createCosts() {
    Optional<ConstructionDTO> findBy = constructionService.findById("10c43b61-6a45-4c0a-9ee1-f729e3d977bb");

    if (findBy.isPresent()) {
      ConstructionDTO update = findBy.get();
      update.setCode("codeUpdateHello");
      update.setTotalMoney(new BigDecimal(15412333));
      update.setName("26bb");
      update.setStatus(StatusConstruction.DOING);
      constructionService.update(update);
      return BaseResponse.handlerResponseCustom(StatusCode.OK, Message.SUCCESS);
    }

    return BaseResponse.handlerResponseCustom(StatusCode.NO_CONTENT, Message.NO_CONTENT);
  }

}
