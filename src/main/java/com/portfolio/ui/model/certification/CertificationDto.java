package com.portfolio.ui.model.certification;

import com.portfolio.model.TranslationString;
import com.portfolio.ui.model.GenericUiDataModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class CertificationDto extends GenericUiDataModel {

    private Long id;
    private TranslationString name;
    private String issuer;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String[] categories;
    private TranslationString status;
    private Map<String, String[]> skills;
    private String badge;
    private TranslationString description;
}
