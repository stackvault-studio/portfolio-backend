package com.portfolio.ui.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Builder(builderMethodName = "genericUiBuilder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericUiDataModel {
    private OffsetDateTime submittedAt = OffsetDateTime.now();
}
