package com.portfolio.ui.model.experience;

import com.portfolio.ui.model.GenericUiDataModel;
import com.portfolio.ui.model.dashboard.Stats;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "experienceBuilder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperiencesTimelineUiDataModel extends GenericUiDataModel {

    private List<ExperienceCardUiDataModel> experiences;
    private Stats stats;
}
