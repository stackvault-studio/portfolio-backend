package com.portfolio.ui.model;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class GenericUiResponseModel<T> {
    private boolean success;
    private String message;
    private OffsetDateTime timestamp;
    private T data;
}
