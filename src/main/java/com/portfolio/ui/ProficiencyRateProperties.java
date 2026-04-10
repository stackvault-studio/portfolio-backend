package com.portfolio.ui;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data
@ConfigurationProperties(prefix = "proficiency")
public class ProficiencyRateProperties {
    private Map<String, Integer> rates;
    private String defaultProficiency;
}
