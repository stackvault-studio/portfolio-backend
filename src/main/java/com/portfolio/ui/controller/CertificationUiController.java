package com.portfolio.ui.controller;

import com.portfolio.ui.model.GenericUiResponseModel;
import com.portfolio.ui.model.certification.CertificationUiDataModel;
import com.portfolio.ui.service.CertificationUiService;
import com.portfolio.ui.service.GenericUiResponseModelMapperUtility;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ui/certifications")
public class CertificationUiController {

    private final CertificationUiService certificationUiService;

    @GetMapping
    public ResponseEntity<GenericUiResponseModel<CertificationUiDataModel>> getCertificationsDetails() {
        return ResponseEntity.ok(GenericUiResponseModelMapperUtility.mapData(certificationUiService::getCertificationsDetails));
    }
}
