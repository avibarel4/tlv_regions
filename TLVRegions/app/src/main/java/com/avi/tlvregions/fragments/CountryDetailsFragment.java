package com.avi.tlvregions.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avi.tlvregions.App;
import com.avi.tlvregions.R;
import com.avi.tlvregions.Utils;
import com.avi.tlvregions.network.IResponse;
import com.avi.tlvregions.network.Response;
import com.avi.tlvregions.network.controllers.GetCountriesByCodesController;
import com.avi.tlvregions.network.objects.Country;
import com.avi.tlvregions.network.objects.Currency;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avibarel on 21/10/2017.
 */

public class CountryDetailsFragment extends Fragment implements IResponse<Response<List<Country>>> {

    private static final String EXTRA_KEY_COUNTRY = "extra_key_country";

    private String mCountryName;

    private static final int BORDER_COUNTRY_PADDING_HORIZONTAL = App.getContext().getResources().getDimensionPixelSize(R.dimen.border_country_padding_horizontal);
    private static final int BORDER_COUNTRY_PADDING_VERTICAL = App.getContext().getResources().getDimensionPixelSize(R.dimen.border_country_padding_vertical);

    private LinearLayout mLayoutBorders;
    private List<Country> mBorderCountries;

    public static CountryDetailsFragment newInstance(Country country) {

        Bundle args = new Bundle();
        args.putParcelable(EXTRA_KEY_COUNTRY, country);

        CountryDetailsFragment fragment = new CountryDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Country country = getArguments().getParcelable(EXTRA_KEY_COUNTRY);
        mCountryName = country.getName();

        View view = inflater.inflate(R.layout.fragment_country_details, container, false);

        TextView textCapital = (TextView) view.findViewById(R.id.text_capital);
        TextView textCurrencies = (TextView) view.findViewById(R.id.text_currencies);
        mLayoutBorders = (LinearLayout) view.findViewById(R.id.layout_borders);

        textCapital.setText(country.getCapital());

        // get all the currencies String for the given country
        String currenciesString = Utils.getCurrenciesStringForCountry(country, getString(R.string.currency_format));

        textCurrencies.setText(currenciesString);

        new GetCountriesByCodesController(country.getBorders())
            .setListener(this)
            .start();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().setTitle(mCountryName);

        setBordersData();
    }

    private void setBordersData() {

        if (!isAdded() || mBorderCountries == null) {
            return;
        }

        mLayoutBorders.removeAllViews(); // remove the progress

        for (Country border : mBorderCountries) {
            TextView textViewBorder = new TextView(getActivity());
            textViewBorder.setText(border.getName());

            textViewBorder.setTextSize(20);
            textViewBorder.setPadding(BORDER_COUNTRY_PADDING_HORIZONTAL, BORDER_COUNTRY_PADDING_VERTICAL, BORDER_COUNTRY_PADDING_HORIZONTAL, BORDER_COUNTRY_PADDING_VERTICAL);
            textViewBorder.setGravity(Gravity.CENTER);

            mLayoutBorders.addView(textViewBorder, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mLayoutBorders = null;
        mBorderCountries = null;
    }


    @Override
    public void onSuccess(Response<List<Country>> result) {
        mBorderCountries = result.getData();
        if (isAdded()) {
            setBordersData();
        }
    }

    @Override
    public void onError(Throwable t) {
        mBorderCountries = new ArrayList<>();
        if (isAdded()) {
            setBordersData();
        }
    }
}
