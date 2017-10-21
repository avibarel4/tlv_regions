package com.avi.tlvregions.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avi.tlvregions.MainActivity;
import com.avi.tlvregions.R;
import com.avi.tlvregions.adapters.RegionsAdapter;

/**
 * Created by avibarel on 21/10/2017.
 */

public class RegionsFragment extends Fragment implements RegionsAdapter.OnRegionClickedCallback {

    private RegionsAdapter mRegionsAdapter;

    public static RegionsFragment newInstance() {
        Bundle args = new Bundle();

        RegionsFragment fragment = new RegionsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regions, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_regions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRegionsAdapter = new RegionsAdapter();
        recyclerView.setAdapter(mRegionsAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        mRegionsAdapter.setListener(this);

        getActivity().setTitle("Select Region");
    }

    @Override
    public void onPause() {
        super.onPause();

        mRegionsAdapter.setListener(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mRegionsAdapter = null;
    }

    @Override
    public void onRegionClicked(String region) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).regionSelected(region);
        }
    }
}
