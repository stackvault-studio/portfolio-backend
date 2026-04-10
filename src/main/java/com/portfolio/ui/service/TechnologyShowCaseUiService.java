package com.portfolio.ui.service;

import com.portfolio.repository.TechnologyTopicRateRepository;
import com.portfolio.ui.model.technology.TechnologiesShowCaseUiDataModel;
import com.portfolio.ui.model.technology.TechnologyTopicRateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TechnologyShowCaseUiService {

    private final TechnologyTopicRateRepository technologyTopicRateRepository;
    private final TechnologyTopicRateMapper technologyTopicRateMapper;

    public TechnologiesShowCaseUiDataModel getTechnologiesShowCaseUiDatal() {
        return technologyTopicRateMapper.apply(technologyTopicRateRepository.findDistinctTechnologies());
    }
}
