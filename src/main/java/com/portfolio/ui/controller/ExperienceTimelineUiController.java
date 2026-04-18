package com.portfolio.ui.controller;

import com.portfolio.model.WorkExperience;
import com.portfolio.ui.model.experience.ExperiencesTimelineUiDataModel;
import com.portfolio.ui.model.GenericUiResponseModel;
import com.portfolio.ui.service.ExperienceUiService;
import com.portfolio.ui.service.GenericUiResponseModelMapperUtility;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ui/experience-timeline")
@AllArgsConstructor
public class ExperienceTimelineUiController {

    private final ExperienceUiService experienceService;

    @GetMapping
    public ResponseEntity<GenericUiResponseModel<ExperiencesTimelineUiDataModel>> getExperiencesForTimeline() {
        return ResponseEntity.ok(GenericUiResponseModelMapperUtility.mapData(experienceService::getExperienceUiDataModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericUiResponseModel<WorkExperience>> getExperienceData(@PathVariable Long id) {
        return ResponseEntity.ok(GenericUiResponseModelMapperUtility.mapData(() -> experienceService.getWorkExperienceById(id)));
    }
}
