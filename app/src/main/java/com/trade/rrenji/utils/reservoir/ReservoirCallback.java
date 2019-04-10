package com.trade.rrenji.utils.reservoir;

public interface ReservoirCallback<T> {
    void onSuccess(T data);

    void onFailure(Exception e);
}
