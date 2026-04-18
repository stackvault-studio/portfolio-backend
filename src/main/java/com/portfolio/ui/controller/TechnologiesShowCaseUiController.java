package com.portfolio.ui.controller;

import com.portfolio.ui.model.GenericUiResponseModel;
import com.portfolio.ui.model.technology.TechnologiesShowCaseUiDataModel;
import com.portfolio.ui.service.GenericUiResponseModelMapperUtility;
import com.portfolio.ui.service.TechnologyShowCaseUiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ui/technologies-showcase")
@RequiredArgsConstructor
public class TechnologiesShowCaseUiController {

    private final TechnologyShowCaseUiService technologyShowCaseUiServicetechnologyShowCaseUiService;

    @GetMapping
    public ResponseEntity<GenericUiResponseModel<TechnologiesShowCaseUiDataModel>> getTechnologyShowCase() {
        return ResponseEntity.ok(GenericUiResponseModelMapperUtility.mapData(technologyShowCaseUiServicetechnologyShowCaseUiService::getTechnologiesShowCaseUiDatal));
    }
}
