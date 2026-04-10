package com.portfolio.ui.model.education;

import com.portfolio.model.TranslationString;
import com.portfolio.ui.model.GenericUiDataModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class EducationDto extends GenericUiDataModel {

    private Long id;
    private TranslationString degree;
    private String institution;
    private TranslationString location;
    private TranslationString duration;
    private Long gpa;
    private Integer graduationYear;
    private Map<String, List<String>> coursework;
    private Map<String, List<String>> projects;
    private Map<String, List<String>> achievements;
}
