package com.avi.tlvregions.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avi.tlvregions.R;
import com.avi.tlvregions.network.objects.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avibarel on 21/10/2017.
 */

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> implements View.OnClickListener {

    private List<Country> mCountries;

    private OnCountryClickedCallback mListener;

    public CountriesAdapter() {
        mCountries = new ArrayList<>();
    }

    public void setListener(OnCountryClickedCallback listener) {
        mListener = listener;
    }

    public void setData(List<Country> countries) {
        mCountries = countries;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_country, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mViewMain.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mViewMain.setTag(position);

        Country country = mCountries.get(position);

        holder.mTextCountry.setText(country.getName());
        holder.mTextArea.setText(String.format("%,d km²", country.getArea())); // for the coma seperator
    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    @Override
    public void onClick(View v) {
        Country country = mCountries.get((Integer) v.getTag());
        if (mListener != null) {
            mListener.onCountryClicked(country);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private View mViewMain;
        private TextView mTextCountry;
        private TextView mTextArea;

        public ViewHolder(View itemView) {
            super(itemView);

            mViewMain = itemView.findViewById(R.id.layout_main);
            mTextCountry = (TextView) itemView.findViewById(R.id.text_country);
            mTextArea = (TextView) itemView.findViewById(R.id.text_area);
        }
    }

    public interface OnCountryClickedCallback {
        void onCountryClicked(Country country);
    }

}
