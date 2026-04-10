package com.portfolio.ui;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data
@ConfigurationProperties
public class LogoProperties {
    private Map<String, String> logos;

    public String getLogoForTechnology(String technology) {
        if (logos == null) return null;
        return logos.getOrDefault(technology, logos.get("default"));
    }

}
