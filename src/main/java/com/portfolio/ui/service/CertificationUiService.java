package com.portfolio.ui.service;

import com.portfolio.repository.CertificationRepository;
import com.portfolio.ui.model.GenericUiResponseModel;
import com.portfolio.ui.model.certification.CertificationMapper;
import com.portfolio.ui.model.certification.CertificationUiDataModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CertificationUiService {

    private final CertificationRepository certificationRepository;
    private final CertificationMapper certificationMapper;

    public CertificationUiDataModel getCertificationsDetails() {
        return CertificationUiDataModel.certificationsBuilder()
                .certifications(certificationRepository.findAll().stream().map(certificationMapper).toList())
                .build();
    }
}
