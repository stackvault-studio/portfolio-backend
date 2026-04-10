package com.portfolio.controller;

import com.portfolio.model.WorkExperience;
import com.portfolio.service.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/work-experiences")
public class WorkExperienceController {

    @Autowired
    private WorkExperienceService workExperienceService;

    @GetMapping
    public List<WorkExperience> getAllWorkExperiences() {
        return workExperienceService.getAllWorkExperiences();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkExperience> getWorkExperienceById(@PathVariable Long id) {
        return workExperienceService.getWorkExperienceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public WorkExperience createWorkExperience(@RequestBody WorkExperience workExperience) {
        return workExperienceService.createWorkExperience(workExperience);
    }

    @PutMapping("/{id}")
    public WorkExperience updateWorkExperience(@PathVariable Long id, @RequestBody WorkExperience workExperience) {
        return workExperienceService.updateWorkExperience(id, workExperience);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkExperience(@PathVariable Long id) {
        workExperienceService.deleteWorkExperience(id);
        return ResponseEntity.noContent().build();
    }
}
