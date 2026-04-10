package com.portfolio.ui.model.certification;

import com.portfolio.ui.model.GenericUiDataModel;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "certificationsBuilder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationUiDataModel extends GenericUiDataModel {

    private List<CertificationDto> certifications;
}
