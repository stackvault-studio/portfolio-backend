package com.portfolio.ui.model;

import com.portfolio.model.TranslationString;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

public class MapperUtility {

    public static TranslationString buildDurationBetween(OffsetDateTime startDate, OffsetDateTime endDate) {
        var result = new TranslationString();
        if (startDate != null) {
            if (endDate == null) {
                endDate = OffsetDateTime.now();
            }
            var years = ChronoUnit.YEARS.between(startDate, endDate);
            var months = ChronoUnit.MONTHS.between(startDate, endDate) - 12 * (years);
            StringBuilder durationEn = new StringBuilder();
            StringBuilder durationFr = new StringBuilder();
            if (years > 0) {
                durationEn.append(years).append(" years");
                durationFr.append(years).append(" ans");
                if (months > 0) {
                    durationEn.append(", ");
                    durationFr.append(", ");
                }
            }
            if (months > 0) {
                durationEn.append(months).append(" months");
                durationFr.append(months).append(" mois");
            }
            result.setEn(durationEn.toString());
            result.setFr(durationFr.toString());
        }
        return result;
    }
}
