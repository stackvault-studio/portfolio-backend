package com.portfolio.ui.model.technology;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TechnologiesShowCaseStatsUiDto {
    private int technologiesCount;
    private int expertProficiencyCount;
    private int categoriesCount;
    private int certificationCount;
    private List<TechnologySkillOverview> technologySkillOverviewList;
}
