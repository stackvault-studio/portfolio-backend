package com.portfolio.ui.model.experience;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class ExperienceCardUiDataModel {

    private Long id;
    private String company;
    private Map<String, String> description;
    private String companyLogo;
    private Map<String, String> position;
    private LocalDate startDate;
    private LocalDate endDate;
    private Map<String, String> duration;
    private List<String> technologies;
    private List<String> clients;
    public ExperienceCardUiDataModel(Long id, String posEng, String posFr, String company, String descEng, String descFr, String companyLogo, LocalDate startDate, LocalDate endDate, String technologies, String clientsNames) {
        this.id = id;
        this.position = Map.of("en", posEng, "fr", posFr);
        this.company = company;
        this.description = Map.of("en", descEng, "fr", descFr);
        this.companyLogo = companyLogo;
        this.startDate = startDate;
        if (endDate != null) {
            this.endDate = endDate;
            var durationInMonths = ChronoUnit.MONTHS.between(this.startDate, this.endDate);
            this.duration = Map.of("en", durationInMonths + " Months", "fr", durationInMonths + " Mois");
        }
        this.technologies = technologies != null ? Arrays.stream(technologies.split(",")).toList() : null;
        this.clients = clientsNames != null ? Arrays.stream(clientsNames.split(",")).toList() : null;
    }
}
