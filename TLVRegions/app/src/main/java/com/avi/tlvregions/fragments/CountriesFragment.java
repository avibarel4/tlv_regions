package com.avi.tlvregions.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avi.tlvregions.adapters.CountriesAdapter;
import com.avi.tlvregions.MainActivity;
import com.avi.tlvregions.R;
import com.avi.tlvregions.network.IResponse;
import com.avi.tlvregions.network.Response;
import com.avi.tlvregions.network.controllers.GetCountriesByRegionController;
import com.avi.tlvregions.network.objects.Country;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by avibarel on 18/10/2017.
 */

public class CountriesFragment extends Fragment implements IResponse<Response<List<Country>>>, CountriesAdapter.OnCountryClickedCallback {

    private final static String EXTRA_KEY_REGION = "extra_key_region";

    private List<Country> mCountries;
    private CountriesAdapter mCountriesAdapter;

    private String mRegion;

    public static CountriesFragment newInstance(String region) {

        Bundle args = new Bundle();
        args.putString(EXTRA_KEY_REGION, region);

        CountriesFragment fragment = new CountriesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRegion = getArguments().getString(EXTRA_KEY_REGION);

        View view = inflater.inflate(R.layout.fragment_countries, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_countries);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mCountriesAdapter = new CountriesAdapter();
        recyclerView.setAdapter(mCountriesAdapter);

        new GetCountriesByRegionController(mRegion).setListener(this).start();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        mCountriesAdapter.setListener(this);

        updateData();

        getActivity().setTitle(mRegion);
    }

    @Override
    public void onPause() {
        super.onPause();

        mCountriesAdapter.setListener(null);

        // make sure the progress is closed in case we close the fragment before the data returned
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).toggleProgress(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mCountries = null;
        mCountriesAdapter = null;
    }

    @Override
    public void onSuccess(Response<List<Country>> result) {
        mCountries = result.getData();
        Collections.sort(mCountries); // sort by area
        updateData();
    }

    @Override
    public void onError(Throwable t) {
        mCountries = new ArrayList<>();
        updateData();
    }

    private void updateData() {
        if (isAdded()) {
            boolean dispayProgress;
            if (mCountries == null) {
                dispayProgress = true;
            } else {
                dispayProgress = false;
                mCountriesAdapter.setData(mCountries);
            }

            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).toggleProgress(dispayProgress);
            }
        }
    }

    @Override
    public void onCountryClicked(Country country) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).countrySelected(country);
        }
    }
}
