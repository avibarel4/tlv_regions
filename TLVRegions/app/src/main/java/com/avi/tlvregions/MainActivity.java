package com.avi.tlvregions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.avi.tlvregions.fragments.CountriesFragment;
import com.avi.tlvregions.fragments.CountryDetailsFragment;
import com.avi.tlvregions.fragments.RegionsFragment;
import com.avi.tlvregions.network.objects.Country;

public class MainActivity extends AppCompatActivity {

    private View mLayoutProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayoutProgress = findViewById(R.id.layout_progress);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, RegionsFragment.newInstance())
                    .commit();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLayoutProgress = null;
    }

    public void toggleProgress(boolean show) {
        if (mLayoutProgress != null) {
            mLayoutProgress.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    public void regionSelected(String region) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, CountriesFragment.newInstance(region))
                .addToBackStack(null)
                .commit();
    }

    public void countrySelected(Country country) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, CountryDetailsFragment.newInstance(country))
                .addToBackStack(null)
                .commit();
    }
}
