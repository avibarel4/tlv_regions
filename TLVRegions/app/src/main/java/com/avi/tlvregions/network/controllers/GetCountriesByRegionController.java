package com.avi.tlvregions.network.controllers;

import com.avi.tlvregions.network.Response;
import com.avi.tlvregions.network.UrlFactory;
import com.avi.tlvregions.network.objects.Country;

import java.util.List;

import retrofit2.Call;

/**
 * Created by avibarel on 18/10/2017.
 */

public class GetCountriesByRegionController extends AbsController<List<Country>> {

    private String mRegion;

    public GetCountriesByRegionController(String region) {
        mRegion = region;
    }

    @Override
    public void start() {
        UrlFactory.API api = buildRetrofit().create(UrlFactory.API.class);
        mCall = api.getCountriesByRegion(mRegion);
        mCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Country>> call, retrofit2.Response<List<Country>> response) {

        if (mListener != null) {
            if (response == null || response.body() == null) {
                mListener.onError(new Throwable("Failed to get response"));
            } else {
                mListener.onSuccess(new Response<>(response.body(), getExtra()));
            }
        }

        super.onResponse(call, response);
    }

    @Override
    public void onFailure(Call<List<Country>> call, Throwable t) {
        super.onFailure(call, t);
    }
}
