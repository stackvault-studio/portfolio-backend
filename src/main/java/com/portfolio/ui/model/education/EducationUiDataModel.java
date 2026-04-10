package com.portfolio.ui.model.education;

import com.portfolio.ui.model.GenericUiDataModel;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "educationBuilder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationUiDataModel extends GenericUiDataModel {
    private List<EducationDto> educations;
}
