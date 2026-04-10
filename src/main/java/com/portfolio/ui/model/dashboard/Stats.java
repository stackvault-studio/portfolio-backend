package com.portfolio.ui.model.dashboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder(builderMethodName = "statsBuilder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stats {

    private Long totalProjects;
    private Long totalClients;
    private Long totalCertifications;
    private Long totalTechnologies;
    private BigDecimal yearsExperience;

}
