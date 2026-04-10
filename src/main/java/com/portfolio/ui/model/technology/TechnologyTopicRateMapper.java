package com.portfolio.ui.model.technology;

import com.portfolio.model.Project;
import com.portfolio.model.TechnologyTopicRate;
import com.portfolio.model.TranslationString;
import com.portfolio.service.CertificationService;
import com.portfolio.ui.LogoProperties;
import com.portfolio.ui.ProficiencyRateProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.portfolio.ui.model.MapperUtility.buildDurationBetween;

@Component
@RequiredArgsConstructor
public class TechnologyTopicRateMapper implements Function<List<TechnologyTopicRate>, TechnologiesShowCaseUiDataModel> {

    public static final String DATE_PATTERN = "d MMMM yyyy";
    private static final DateTimeFormatter englishFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN, Locale.ENGLISH);
    private static final DateTimeFormatter frenchFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN, Locale.FRENCH);
    private final LogoProperties logoProperties;
    private final ProficiencyRateProperties proficiencyRateProperties;
    private final CertificationService certificationService;
    @Value("${recent.project.duration:P5Y}")
    private Period recentDuration;
    @Value("${recent.project.count:3}")
    private int recentCount;

    private static TranslationString buildLastUsed(List<TechnologyTopicRate> technologies) {
        TranslationString result = new TranslationString();
        if (technologies.stream().anyMatch(technologyTopicRate -> technologyTopicRate.getProject().getEndDate() == null)) {
            result.setEn("Currently in use");
            result.setFr("Actuellement utilisée");
        } else {
            var lastUsedDate = technologies.stream().map(tech -> tech.getProject().getEndDate()).max(OffsetDateTime::compareTo);
            if (lastUsedDate.isPresent()) {
                result.setEn(lastUsedDate.get().format(englishFormatter));
                result.setFr(lastUsedDate.get().format(frenchFormatter));
            }
        }
        return result;
    }

    @Override
    public TechnologiesShowCaseUiDataModel apply(List<TechnologyTopicRate> technologies) {
        var technologiesByName = technologies.stream()
                .collect(Collectors.groupingBy(TechnologyTopicRate::getName));
        var technologiesResult = technologiesByName.values().stream().map(techs ->
                TechnologyShowCaseDto.technologiesShowCaseBuilder()
                        .name(techs.getFirst().getName())
                        .projectsCount(Math.toIntExact(techs.stream().map(TechnologyTopicRate::getProject).distinct().count()))
                        .tags(techs.stream().flatMap(tech -> Stream.of(tech.getCoveredTopic().split(",")).map(String::strip)).distinct().toArray(String[]::new))
                        .category(techs.getFirst().getTechnology().name())
                        .logo(logoProperties.getLogoForTechnology(techs.getFirst().getName().replace(" ", "")))
                        .recentlyUsed(isRecentlyUsed(techs.stream().map(TechnologyTopicRate::getProject).filter(Objects::nonNull).toList()))
                        .yearsOfExperience(extractYearsOfExperience(techs))
                        .lastUsed(buildLastUsed(techs))
                        .recentProjects(buildRecentProjects(techs.stream().map(TechnologyTopicRate::getProject).toList()))
                        .proficiency(buildProficiency(techs))
                        //missing frameworks
                        .build()).toList();

        var skillStatsOverview = technologies.stream()
                .collect(Collectors.groupingBy(TechnologyTopicRate::getTechnology)).entrySet()
                .stream()
                .map(entry -> TechnologySkillOverview.builder()
                        .category(entry.getKey().name())
                        .proficiency(buildOverviewProficiency(entry.getValue()) * 10)
                        .experience(buildOverviewExperience(entry.getValue()))
                        .build()
                ).toList();

        return TechnologiesShowCaseUiDataModel.technologiesShowCaseUiDataModelBuilder()
                .technologies(technologiesResult)
                .stats(TechnologiesShowCaseStatsUiDto.builder()
                        .technologiesCount(technologiesByName.size())
                        .categoriesCount(Math.toIntExact(technologiesResult.stream().map(TechnologyShowCaseDto::getCategory).distinct().count()))
                        .certificationCount(certificationService.getCertificationCount())
                        .expertProficiencyCount(calculateExpertCount(technologiesByName))
                        .technologySkillOverviewList(skillStatsOverview)
                        .build())
                .build();
    }

    private int calculateExpertCount(Map<String, List<TechnologyTopicRate>> technologiesByName) {
        return Math.toIntExact(technologiesByName.entrySet().stream()
                .filter(entry -> "Expert".equalsIgnoreCase(buildProficiency(entry.getValue())))
                .count());
    }

    private double buildOverviewExperience(List<TechnologyTopicRate> value) {
        var projectsInvolved = value.stream().map(TechnologyTopicRate::getProject).distinct().toList();
        if (!projectsInvolved.isEmpty()) {
            var startDate = projectsInvolved.stream().map(Project::getStartDate).min(OffsetDateTime::compareTo);
            if (projectsInvolved.stream().anyMatch(project -> project.getEndDate() == null)) {
                return calculateYearsBetween(startDate.get(), OffsetDateTime.now());
            }
            var endDate = projectsInvolved.stream().map(Project::getEndDate).max(OffsetDateTime::compareTo);
            return calculateYearsBetween(startDate.get(), endDate.get());
        }
        return 0;
    }

    private double buildOverviewProficiency(List<TechnologyTopicRate> technologyTopicRates) {
        return technologyTopicRates.stream().map(TechnologyTopicRate::getRate).mapToDouble(BigDecimal::doubleValue).average().orElse(0);
    }

    private double calculateYearsBetween(OffsetDateTime startDate, OffsetDateTime endDate) {
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return daysBetween / 365.2425;
    }

    private TranslationString[] buildRecentProjects(List<Project> projects) {
        List<TranslationString> resultList = new ArrayList<>();
        var currentProject = projects.stream().filter(p -> p.getEndDate() == null).findAny();
        currentProject.ifPresent(project -> resultList.add(buildRecentProject(project)));
        var recentProjects = projects.stream()
                .sorted(Comparator.comparing(Project::getEndDate).reversed())
                .limit(currentProject.isPresent() ? recentCount - 1 : recentCount)
                .map(this::buildRecentProject)
                .toList();
        resultList.addAll(recentProjects);
        return resultList.toArray(TranslationString[]::new);
    }

    private TranslationString buildRecentProject(Project p) {
        var result = new TranslationString();
        result.setEn(p.getProjectName() + ": " + p.getProjectDescription().getEn());
        result.setFr(p.getProjectName() + ": " + p.getProjectDescription().getFr());
        return result;
    }

    private TranslationString extractYearsOfExperience(List<TechnologyTopicRate> technologies) {
        var startDate = technologies.stream().map(technologyTopicRate -> technologyTopicRate.getProject().getStartDate()).min(OffsetDateTime::compareTo);
        if (startDate.isPresent()) {
            var currentlyExistingProject = technologies.stream().filter(technologyTopicRate -> technologyTopicRate.getProject().getEndDate() == null).findAny();
            if (currentlyExistingProject.isPresent()) {
                return buildDurationBetween(startDate.get(), OffsetDateTime.now());
            }
            var endDate = technologies.stream().map(technologyTopicRate -> technologyTopicRate.getProject().getEndDate()).max(OffsetDateTime::compareTo);
            return buildDurationBetween(startDate.get(), endDate.get());
        }
        return null;
    }

    private boolean isRecentlyUsed(List<Project> usedProjects) {
        if (usedProjects == null || usedProjects.isEmpty() || usedProjects.stream().anyMatch(project -> project.getEndDate() == null)) {
            return true;
        }
        return usedProjects.stream().map(Project::getEndDate).max(OffsetDateTime::compareTo).filter(this::isRecentDate).isPresent();
    }

    private boolean isRecentDate(OffsetDateTime dateTime) {
        return !recentDuration.minus(Period.between(dateTime.toLocalDate(), OffsetDateTime.now().toLocalDate())).isNegative();
    }

    private String buildProficiency(List<TechnologyTopicRate> technologyTopicRates) {
        var avg = technologyTopicRates.stream().map(TechnologyTopicRate::getRate).mapToDouble(BigDecimal::doubleValue).average();
        if (avg.isPresent()) {
            var proficiency = proficiencyRateProperties.getRates().entrySet().stream()
                    .filter(entry -> entry.getValue() <= avg.getAsDouble())
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(proficiencyRateProperties.getDefaultProficiency());
            return proficiency.substring(0, 1).toUpperCase() + proficiency.substring(1);
        }
        return proficiencyRateProperties.getDefaultProficiency();
    }
}
