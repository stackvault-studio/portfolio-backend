package com.portfolio.ui.model.experience;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder(builderMethodName = "technologyBuilder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Technology {
    private String name;
    private BigDecimal rate;
    private String icon;
}
