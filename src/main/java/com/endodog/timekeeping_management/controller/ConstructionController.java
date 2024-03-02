package com.endodog.timekeeping_management.controller;

import com.endodog.timekeeping_management.constant.ApiConstant;
import com.endodog.timekeeping_management.model.response.construction.ConstructionResponse;
import com.endodog.timekeeping_management.model.response.base.ResponseBase;
import com.endodog.timekeeping_management.repository.ConstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConstant.ConstructionApi.ROOT_API)
public class ConstructionController {

  @Autowired
  private ConstructionRepository constructionRepository;

  @GetMapping
  public ResponseBase<List<ConstructionResponse>> getConstructions() {
//    if (constructionRepository.findAll().isEmpty()) {
//    }
    return new ResponseBase<>(constructionRepository.getAll());
  }


}
