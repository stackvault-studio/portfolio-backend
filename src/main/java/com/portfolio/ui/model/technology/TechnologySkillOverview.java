package com.portfolio.ui.model.technology;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TechnologySkillOverview {
    private String category;
    private double proficiency;
    private double experience;

}
