package com.avi.tlvregions.network;

/**
 * Created by avibarel on 18/10/2017.
 */

public class Response<T> {

    private T mData;
    private Object mExtra;

    public Response(T data, Object extra) {
        mData = data;
        mExtra = extra;
    }

    public T getData() {
        return mData;
    }

    public Object getExtra() {
        return mExtra;
    }

}
