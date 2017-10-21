package com.avi.tlvregions.network;

import com.avi.tlvregions.network.objects.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by avibarel on 18/10/2017.
 */

public class UrlFactory {

    public static final String BASE_URL = "https://restcountries.eu/rest/v2/";

    public interface API {

        @GET("region/{region}")
        Call<List<Country>> getCountriesByRegion(@Path("region") String region);

        @GET("alpha")
        Call<List<Country>> getCountriesByCodes(@Query("codes") String codes);

    }

}
