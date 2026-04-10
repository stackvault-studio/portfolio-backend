package com.portfolio.ui.service;

import com.portfolio.ui.model.GenericUiResponseModel;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.util.function.Supplier;

@Slf4j
public class GenericUiResponseModelMapperUtility {

    public static <T> GenericUiResponseModel<T> mapData(Supplier<T> dataProducer) {
        var builder = GenericUiResponseModel.<T>builder()
                .timestamp(OffsetDateTime.now());
        try {
            var data = dataProducer.get();
            return builder.data(data)
                    .success(Boolean.TRUE)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return builder.success(Boolean.FALSE)
                    .message(e.getMessage())
                    .build();
        }
    }
}
