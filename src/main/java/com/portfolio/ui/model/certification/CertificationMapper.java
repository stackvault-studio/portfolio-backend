package com.portfolio.ui.model.certification;

import com.portfolio.model.Certification;
import com.portfolio.model.TechnologyTopicRate;
import com.portfolio.model.TranslationString;
import com.portfolio.ui.model.AbstractUiDataMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CertificationMapper implements AbstractUiDataMapper<Certification, CertificationDto> {

    @Override
    public CertificationDto apply(Certification certification) {
        return CertificationDto.builder()
                .id(certification.getId())
                .name(certification.getName())
                .issuer(certification.getIssuer())
                .description(certification.getDescription())
                .badge(certification.getBadgeUrl())
                .expiryDate(certification.getExpiryDate())
                .issueDate(certification.getIssueDate())
                .categories(buildCategories(certification.getTechnologies()))
                .status(buildStatus(certification.isActive()))
                .skills(buildSkills(certification.getTechnologies()))
                .build();
    }

    private String[] buildCategories(List<TechnologyTopicRate> technologies) {
        return technologies
                .stream()
                .map(technologyTopicRate -> technologyTopicRate.getTechnology().name())
                .distinct()
                .toArray(String[]::new);
    }

    private TranslationString buildStatus(boolean isActive) {
        TranslationString status = new TranslationString();
        status.setEn(isActive ? "Active" : "Expired");
        status.setFr(isActive ? "Actif" : "Expiré");
        return status;
    }

    private Map<String, String[]> buildSkills(List<TechnologyTopicRate> technologies) {
        var englishSkills = technologies.stream()
                .map(technologyTopicRate -> technologyTopicRate.getCoveredTopic() + " : " + technologyTopicRate.getDescription().getEn())
                .toArray(String[]::new);
        var frenchSkills = technologies.stream()
                .map(technologyTopicRate -> technologyTopicRate.getCoveredTopic() + " : " + technologyTopicRate.getDescription().getFr())
                .toArray(String[]::new);

        return Map.of("en", englishSkills, "fr", frenchSkills);
    }
}
