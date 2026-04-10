package com.portfolio.ui.service;

import com.portfolio.model.WorkExperience;
import com.portfolio.service.WorkExperienceService;
import com.portfolio.ui.model.experience.ExperiencesTimelineUiDataModel;
import com.portfolio.ui.repository.WorkExperienceUiRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExperienceUiService {

    private final WorkExperienceUiRepository workExperienceRepository;
    private final WorkExperienceService workExperienceService;

    public ExperiencesTimelineUiDataModel getExperienceUiDataModel() {
        return ExperiencesTimelineUiDataModel.experienceBuilder()
                .experiences(workExperienceRepository.getExperienceTimeline())
                .stats(workExperienceRepository.fetchStats())
                .build();
    }

    public WorkExperience getWorkExperienceById(Long id) {
        return workExperienceService.getWorkExperienceById(id).orElse(null);
    }
}
