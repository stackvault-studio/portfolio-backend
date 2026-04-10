package com.portfolio.ui.model.technology;

import com.portfolio.ui.model.GenericUiDataModel;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "technologiesShowCaseUiDataModelBuilder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnologiesShowCaseUiDataModel extends GenericUiDataModel {
    private List<TechnologyShowCaseDto> technologies;
    private TechnologiesShowCaseStatsUiDto stats;
}
