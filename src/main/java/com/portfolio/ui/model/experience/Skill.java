package com.portfolio.ui.model.experience;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder(builderMethodName = "skillBuilder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    private String category;
    private List<Technology> technologies;

}
