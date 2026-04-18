package com.portfolio.ui.controller;

import com.portfolio.ui.model.dashboard.DashboardUiDataModel;
import com.portfolio.ui.model.GenericUiResponseModel;
import com.portfolio.ui.service.DashboardUiService;
import com.portfolio.ui.service.GenericUiResponseModelMapperUtility;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ui/dashboard")
public class DashboardUiController {

    private final DashboardUiService dashboardService;

    public DashboardUiController(DashboardUiService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<GenericUiResponseModel<DashboardUiDataModel>> getDashboardDetails() {
        return ResponseEntity.ok(GenericUiResponseModelMapperUtility.mapData(dashboardService::getDashboardDetails));
    }
}
