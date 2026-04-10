package com.portfolio.ui.model.technology;

import com.portfolio.model.TranslationString;
import lombok.*;

@Builder(builderMethodName = "technologiesShowCaseBuilder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyShowCaseDto {
    private Long id;
    private String name;
    private String category;
    private String proficiency;
    private TranslationString yearsOfExperience;
    private int projectsCount;
    private boolean recentlyUsed;
    private TranslationString lastUsed;
    private String logo;
    private String[] tags;
    private TranslationString[] recentProjects;
    private String[] frameworks;
}
