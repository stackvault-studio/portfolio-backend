package com.portfolio.ui.service;

import com.portfolio.model.TechnologyTopicRate;
import com.portfolio.repository.ProjectRepository;
import com.portfolio.repository.TechnologyTopicRateRepository;
import com.portfolio.ui.LogoProperties;
import com.portfolio.ui.model.dashboard.DashboardUiDataModel;
import com.portfolio.ui.model.experience.Skill;
import com.portfolio.ui.model.experience.Technology;
import com.portfolio.ui.repository.WorkExperienceUiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardUiService {

    private final WorkExperienceUiRepository projectRepository;
    private final TechnologyTopicRateRepository technologyTopicRateRepository;
    private final LogoProperties logoProperties;

    public DashboardUiDataModel getDashboardDetails() {
        var stats = projectRepository.fetchStats();
        var technologies = technologyTopicRateRepository.findDistinctTechnologies();
        var skills = technologies.stream()
                .collect(Collectors.groupingBy(TechnologyTopicRate::getTechnology))
                .entrySet()
                .stream()
                .map(entry -> Skill.skillBuilder().category(entry.getKey().name())
                        .technologies(entry.getValue()
                                .stream()
                                .collect(Collectors.groupingBy(TechnologyTopicRate::getName))
                                .values()
                                .stream()
                                .map(technologyTopicRates -> technologyTopicRates.stream().max(Comparator.comparing(TechnologyTopicRate::getRate)))
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                                .map(tech -> Technology
                                        .technologyBuilder()
                                        .name(tech.getName())
                                        .rate(tech.getRate())
                                        .icon(logoProperties.getLogoForTechnology(tech.getName()))
                                        .build())
                                .toList())
                        .build())
                .toList();
        return DashboardUiDataModel.dashboardBuilder().stats(stats).skills(skills).build();
    }
}
