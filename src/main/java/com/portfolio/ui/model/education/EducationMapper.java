package com.portfolio.ui.model.education;

import com.portfolio.model.*;
import com.portfolio.ui.model.AbstractUiDataMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.portfolio.ui.model.MapperUtility.buildDurationBetween;

@Component
public class EducationMapper implements AbstractUiDataMapper<Education, EducationDto> {
    @Override
    public EducationDto apply(Education education) {
        return EducationDto.builder()
                .id(education.getId())
                .degree(education.getDegree())
                .gpa(education.getGpa())
                .location(buildLocation(education.getLocation()))
                .institution(education.getInstitution())
                .duration(buildDurationBetween(education.getStartDate(), education.getEndDate()))
                .graduationYear(education.getEndDate() != null ? education.getEndDate().getYear() : 0)
                .projects(buildProjects(education.getProjects()))
                .achievements(buildAchievements(education.getAchievements()))
                .coursework(buildCoursework(education.getCoursework()))
                .build();
    }

    private Map<String, List<String>> buildCoursework(List<Coursework> coursework) {
        return Map.of("en", coursework.stream().map(Coursework::getTitle).map(TranslationString::getEn).toList(),
                "fr", coursework.stream().map(Coursework::getTitle).map(TranslationString::getFr).toList());
    }

    private Map<String, List<String>> buildAchievements(List<Achievement> achievements) {
        return Map.of("en", achievements.stream().map(Achievement::getDescription).map(TranslationString::getEn).toList(),
                "fr", achievements.stream().map(Achievement::getDescription).map(TranslationString::getFr).toList());
    }

    private Map<String, List<String>> buildProjects(List<Coursework> projects) {
        return Map.of("en", projects.stream().map(Coursework::getTitle).map(TranslationString::getEn).toList(),
                "fr", projects.stream().map(Coursework::getTitle).map(TranslationString::getFr).toList());
    }

    private TranslationString buildLocation(Location location) {
        var locationResult = new TranslationString();
        if (location != null && location.getCountry() != null && location.getCity() != null) {
            locationResult.setEn(location.getCountry().getEn() + ", " + location.getCity().getEn());
            locationResult.setFr(location.getCountry().getFr() + ", " + location.getCity().getFr());
        }
        return locationResult;
    }
}
