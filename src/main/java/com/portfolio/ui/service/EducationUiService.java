package com.portfolio.ui.service;

import com.portfolio.repository.EducationRepository;
import com.portfolio.ui.model.education.EducationMapper;
import com.portfolio.ui.model.education.EducationUiDataModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EducationUiService {

    private final EducationRepository educationRepository;
    private final EducationMapper educationMapper;

    public EducationUiDataModel getEducationDetails() {
        return EducationUiDataModel.educationBuilder()
                .educations(educationRepository.findAll()
                        .stream()
                        .map(educationMapper)
                        .sorted((educationOne, educationTwo) -> Integer.compare(educationTwo.getGraduationYear(), educationOne.getGraduationYear()))
                        .toList())
                .build();
    }
}
