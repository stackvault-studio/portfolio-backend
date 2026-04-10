package com.portfolio.ui.model.dashboard;

import com.portfolio.ui.model.GenericUiDataModel;
import com.portfolio.ui.model.experience.Skill;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "dashboardBuilder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardUiDataModel extends GenericUiDataModel {

    private Stats stats;

    private List<Skill> skills;

}
