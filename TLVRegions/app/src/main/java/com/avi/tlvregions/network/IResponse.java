package com.avi.tlvregions.network;

/**
 * Created by avibarel on 18/10/2017.
 */

public interface IResponse<T> {
    void onSuccess(T result);
    void onError(Throwable t);
}
