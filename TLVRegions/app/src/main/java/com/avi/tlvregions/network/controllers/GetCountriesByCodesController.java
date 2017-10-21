package com.avi.tlvregions.network.controllers;

import com.avi.tlvregions.Utils;
import com.avi.tlvregions.network.Response;
import com.avi.tlvregions.network.UrlFactory;
import com.avi.tlvregions.network.objects.Country;

import java.util.List;

import retrofit2.Call;

/**
 * Created by avibarel on 18/10/2017.
 */

public class GetCountriesByCodesController extends AbsController<List<Country>> {

    private String mCodes;

    public GetCountriesByCodesController(List<String> codes) {
        mCodes = Utils.getBorderCountriesStringFromList(codes);
    }

    @Override
    public void start() {
        UrlFactory.API api = buildRetrofit().create(UrlFactory.API.class);
        mCall = api.getCountriesByCodes(mCodes);
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
