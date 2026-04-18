package com.portfolio.ui.controller;

import com.portfolio.ui.model.education.EducationUiDataModel;
import com.portfolio.ui.model.GenericUiResponseModel;
import com.portfolio.ui.service.EducationUiService;
import com.portfolio.ui.service.GenericUiResponseModelMapperUtility;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ui/education")
@AllArgsConstructor
public class EducationUiController {

    private final EducationUiService educationService;

    @GetMapping
    public ResponseEntity<GenericUiResponseModel<EducationUiDataModel>> getEducationDetails() {
        return ResponseEntity.ok(GenericUiResponseModelMapperUtility.mapData(educationService::getEducationDetails));
    }

}
