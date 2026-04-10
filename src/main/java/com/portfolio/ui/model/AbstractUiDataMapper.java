package com.portfolio.ui.model;

import java.util.function.Function;

public interface AbstractUiDataMapper<T, R extends GenericUiDataModel> extends Function<T, R> {
}
